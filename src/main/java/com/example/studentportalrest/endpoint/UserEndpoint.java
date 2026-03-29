package com.example.studentportalrest.endpoint;

import com.example.studentportalrest.dto.SaveUserRequest;
import com.example.studentportalrest.dto.UserAuthRequest;
import com.example.studentportalrest.dto.UserAuthResponse;
import com.example.studentportalrest.mapper.UserMapper;
import com.example.studentportalrest.model.User;
import com.example.studentportalrest.repository.UserRepository;
import com.example.studentportalrest.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserEndpoint {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenUtil tokenUtil;

    private final UserMapper userMapper;

    @PostMapping("/auth")
    public ResponseEntity<UserAuthResponse> login(@RequestBody UserAuthRequest userAuthRequest) {

        Optional<User> byEmail = userRepository.findByUsername(userAuthRequest.getUsername());

        if (byEmail.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .build();
        }

        User user = byEmail.get();
        if (passwordEncoder.matches(userAuthRequest.getPassword(), user.getPassword())) {
            return ResponseEntity
                    .ok(UserAuthResponse.builder()
                            .token(tokenUtil.generateToken(user.getUsername()))
                            .name(user.getName())
                            .surname(user.getSurname())
                            .userId(user.getId())
                            .build());
        }

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .build();
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody SaveUserRequest saveUserRequest) {
        if (userRepository.findByUsername(saveUserRequest.getUsername()).isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .build();
        }
        saveUserRequest.setPassword(passwordEncoder.encode(saveUserRequest.getPassword()));
        userRepository.save(userMapper.toEntity(saveUserRequest));
        return ResponseEntity
                .ok()
                .build();
    }


}

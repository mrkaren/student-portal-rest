package com.example.studentportalrest.init;

import com.example.studentportalrest.model.User;
import com.example.studentportalrest.model.UserRole;
import com.example.studentportalrest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EventListenerExampleBean {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Optional<User> byUsername = userRepository.findByUsername("admin@mail.com");
        if (byUsername.isEmpty()) {
            User entity = new User();
            entity.setName("Admin");
            entity.setSurname("Admin");
            entity.setUsername("admin@mail.com");
            entity.setPassword(passwordEncoder.encode("admin"));
            entity.setRole(UserRole.ADMIN);
            userRepository.save(entity);
        }
    }

}

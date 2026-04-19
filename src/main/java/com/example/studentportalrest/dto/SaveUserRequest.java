package com.example.studentportalrest.dto;

import com.example.studentportalrest.model.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveUserRequest {

    @NotBlank(message = "name can't be blank")
    private String name;
    @NotBlank(message = "surname can't be blank")
    private String surname;
    @NotBlank(message = "username can't be blank")
    @Email(message = "username should be email")
    private String username;
    @NotBlank(message = "password can't be blank")
    @Size(min = 6, message = "Password's min length is 6")
    private String password;
    private UserRole role;

}

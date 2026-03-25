package com.example.studentportalrest.dto;

import com.example.studentportalrest.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveUserRequest {

    private String name;
    private String surname;
    private String username;
    private String password;
    private UserRole role;

}

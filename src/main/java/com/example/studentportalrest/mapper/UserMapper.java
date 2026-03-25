package com.example.studentportalrest.mapper;

import com.example.studentportalrest.dto.SaveUserRequest;
import com.example.studentportalrest.dto.UserAuthResponse;
import com.example.studentportalrest.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "role", source = "saveUserRequest.role")
    User toEntity(SaveUserRequest saveUserRequest);

    UserAuthResponse toDto(User user);

}

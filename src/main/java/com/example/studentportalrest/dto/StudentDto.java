package com.example.studentportalrest.dto;

import com.example.studentportalrest.model.Skill;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDto {

    private int id;
    private String name;
    private String surname;
    private String email;
    private String courseName;
    private String address;
    private List<SkillDto> skills;

}

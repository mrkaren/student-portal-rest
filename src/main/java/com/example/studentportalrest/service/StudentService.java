package com.example.studentportalrest.service;


import com.example.studentportalrest.dto.StudentDto;
import com.example.studentportalrest.model.Course;

import java.util.List;

public interface StudentService {

    List<StudentDto> findAll();

    void deleteByCourse(Course course);
}

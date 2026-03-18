package com.example.studentportalrest.service;



import com.example.studentportalrest.dto.CourseDto;
import com.example.studentportalrest.dto.SaveCourseDto;

import java.util.List;

public interface CourseService {

    List<CourseDto> findAll();

    CourseDto findByName(String name);

    CourseDto save(SaveCourseDto saveCourseDto);

    CourseDto update(SaveCourseDto saveCourseDto, int id);

    CourseDto findById(Integer id);

    void deleteById(Integer id);

}

package com.example.studentportalrest.service.impl;

import com.example.studentportalrest.converter.CourseConverter;
import com.example.studentportalrest.dto.CourseDto;
import com.example.studentportalrest.dto.SaveCourseDto;
import com.example.studentportalrest.model.Course;
import com.example.studentportalrest.repository.CourseRepository;
import com.example.studentportalrest.service.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Primary
@Slf4j
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Override
    public List<CourseDto> findAll() {
        log.info("Fetching all courses");
       return CourseConverter.toDtoList(courseRepository.findAll());
    }

    @Override
    public CourseDto findByName(String name) {
        return CourseConverter.toDto(courseRepository.findByName(name));
    }


    @Override
    public CourseDto save(SaveCourseDto saveCourseDto) {
        Course entity = courseRepository.save(CourseConverter.toEntity(saveCourseDto));
        return CourseConverter.toDto(entity);
    }

    @Override
    public CourseDto update(SaveCourseDto saveCourseDto, int id) {
        Course entity = CourseConverter.toEntity(saveCourseDto);
        entity.setId(id);
        return CourseConverter.toDto(courseRepository.save(entity));
    }

    @Override
    public CourseDto findById(Integer id) {
        return CourseConverter.toDto(courseRepository.findById(id).orElse(null));
    }


    @Override
    public void deleteById(Integer id) {
        courseRepository.deleteById(id);
    }

}

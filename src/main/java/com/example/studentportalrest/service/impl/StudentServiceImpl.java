package com.example.studentportalrest.service.impl;

import com.example.studentportalrest.dto.StudentDto;
import com.example.studentportalrest.mapper.StudentMapper;
import com.example.studentportalrest.model.Course;
import com.example.studentportalrest.repository.StudentRepository;
import com.example.studentportalrest.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Primary
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Override
    public List<StudentDto> findAll() {
       return studentMapper.toDtoList(studentRepository.findAll());
    }

    @Override
    public void deleteByCourse(Course course) {
        studentRepository.deleteAllByCourse(course);
    }
}

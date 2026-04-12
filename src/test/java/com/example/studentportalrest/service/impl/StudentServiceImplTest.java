package com.example.studentportalrest.service.impl;

import com.example.studentportalrest.dto.StudentDto;
import com.example.studentportalrest.mapper.StudentMapper;
import com.example.studentportalrest.model.Student;
import com.example.studentportalrest.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private StudentMapper studentMapper;

    @InjectMocks
    private StudentServiceImpl studentService;

    @Test
    void findAll_returnsMappedList() {
        Student entity = new Student();
        entity.setId(1);
        entity.setName("Alice");
        List<Student> entities = List.of(entity);
        List<StudentDto> dtos = List.of(StudentDto.builder().id(1).name("Alice").build());

        when(studentRepository.findAll()).thenReturn(entities);
        when(studentMapper.toDtoList(entities)).thenReturn(dtos);

        List<StudentDto> result = studentService.findAll();

        assertSame(dtos, result);
        verify(studentRepository).findAll();
//        verify(studentRepository, times(2)).findAll();
        verify(studentMapper).toDtoList(entities);
    }

    @Test
    void findAll_emptyRepository_returnsEmptyList() {
        when(studentRepository.findAll()).thenReturn(Collections.emptyList());
        when(studentMapper.toDtoList(Collections.emptyList())).thenReturn(Collections.emptyList());

        List<StudentDto> result = studentService.findAll();

        assertTrue(result.isEmpty());
    }
}

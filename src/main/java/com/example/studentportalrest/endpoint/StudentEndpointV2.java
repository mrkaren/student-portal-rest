package com.example.studentportalrest.endpoint;

import com.example.studentportalrest.dto.StudentDto;
import com.example.studentportalrest.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v2/students")
public class StudentEndpointV2 {

    private final StudentService studentService;


    @GetMapping
    public List<StudentDto> getStudents() {
        return studentService.findAll();
    }

}

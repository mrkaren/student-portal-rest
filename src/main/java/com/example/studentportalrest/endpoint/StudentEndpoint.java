package com.example.studentportalrest.endpoint;

import com.example.studentportalrest.dto.StudentDto;
import com.example.studentportalrest.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StudentEndpoint {

    private final StudentService studentService;


    @GetMapping("/students")
    public List<StudentDto> getStudents() {
        return studentService.findAll();
    }

}

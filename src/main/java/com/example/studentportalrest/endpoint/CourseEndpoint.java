package com.example.studentportalrest.endpoint;

import com.example.studentportalrest.dto.CourseDto;
import com.example.studentportalrest.dto.SaveCourseDto;
import com.example.studentportalrest.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CourseEndpoint {

    private final CourseService courseService;

    @GetMapping("/courses/{id}")
    public CourseDto getCourseById(@PathVariable int id) {
        return courseService.findById(id);
    }

    @GetMapping("/courses")
    public List<CourseDto> getAllCourses() {
        return courseService.findAll();
    }

    @PostMapping("/courses")
    @Operation(summary = "Save a new course", description = "Save a new course")
    public ResponseEntity<CourseDto> saveCourse(@RequestBody SaveCourseDto saveCourseDto) {
        if (courseService.findByName(saveCourseDto.getCourseName()) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        CourseDto dto = courseService.save(saveCourseDto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/courses/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable int id) {
        if (courseService.findById(id) != null) {
            courseService.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/courses/{id}")
    public CourseDto updateCourse(@PathVariable int id, @RequestBody SaveCourseDto saveCourseDto) {
        return courseService.update(saveCourseDto, id);
    }

}

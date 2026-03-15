package com.example.studentportalrest.endpoint;

import com.example.studentportalrest.model.Course;
import com.example.studentportalrest.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
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

    private final CourseRepository courseRepository;

    @GetMapping("/courses/{id}")
    public Course getCourseById(@PathVariable int id) {
        return courseRepository.findById(id).orElse(null);
    }

    @GetMapping("/courses")
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @PostMapping("/courses")
    public Course saveCourse(@RequestBody Course course) {
        course.setId(0);
        return courseRepository.save(course);
    }

    @DeleteMapping("/courses/{id}")
    public void deleteCourse(@PathVariable int id) {
        courseRepository.findById(id).orElseThrow();
        courseRepository.deleteById(id);
    }

    @PutMapping("/courses/{id}")
    public Course updateCourse(@PathVariable int id, @RequestBody Course course) {
        course.setId(id);
        return courseRepository.save(course);
    }

}

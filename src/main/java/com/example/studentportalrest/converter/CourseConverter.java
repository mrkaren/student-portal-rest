package com.example.studentportalrest.converter;

import com.example.studentportalrest.dto.CourseDto;
import com.example.studentportalrest.dto.SaveCourseDto;
import com.example.studentportalrest.model.Course;
import java.util.Collections;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class CourseConverter {

    public static Course toEntity(SaveCourseDto saveCourseDto) {
        if (saveCourseDto == null) {
            return null;
        }
        return Course.builder()
                .name(saveCourseDto.getName())
                .price(saveCourseDto.getPrice())
                .build();
    }

    public static CourseDto toDto(Course course) {
        if (course == null) {
            return null;
        }
        return CourseDto.builder()
                .id(course.getId())
                .name(course.getName())
                .price(course.getPrice())
                .build();
    }

    public static List<CourseDto> toDtoList(List<Course> courses) {
        if (courses == null) {
            return Collections.emptyList();
        }
        return courses.stream().map(CourseConverter::toDto).toList();
    }

}

package com.example.studentportalrest.service.impl;

import com.example.studentportalrest.dto.CourseDto;
import com.example.studentportalrest.dto.SaveCourseDto;
import com.example.studentportalrest.mapper.CourseMapper;
import com.example.studentportalrest.model.Course;
import com.example.studentportalrest.repository.CourseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CourseServiceImplTest {

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private CourseMapper courseMapper;

    @InjectMocks
    private CourseServiceImpl courseService;

    @Test
    void findAll_returnsMappedList() {
        List<Course> entities = List.of(new Course(1, "Java", 100.0));
        List<CourseDto> dtos = List.of(CourseDto.builder().id(1).name("Java").build());
        when(courseRepository.findAll()).thenReturn(entities);
        when(courseMapper.toDtoList(entities)).thenReturn(dtos);

        List<CourseDto> result = courseService.findAll();

        assertSame(dtos, result);
        verify(courseRepository).findAll();
        verify(courseMapper).toDtoList(entities);
    }

    @Test
    void findByName_returnsMappedDto() {
        Course entity = new Course(1, "Java", 100.0);
        CourseDto dto = CourseDto.builder().id(1).name("Java").build();
        when(courseRepository.findByName("Java")).thenReturn(entity);
        when(courseMapper.toDto(entity)).thenReturn(dto);

        CourseDto result = courseService.findByName("Java");

        assertSame(dto, result);
    }

    @Test
    void findByName_notFound_mapsNull() {
        when(courseRepository.findByName("missing")).thenReturn(null);
        when(courseMapper.toDto((Course) null)).thenReturn(null);

        assertNull(courseService.findByName("missing"));
    }

    @Test
    void save_mapsEntitySavesAndReturnsDto() {
        SaveCourseDto saveDto = SaveCourseDto.builder().courseName("Java").price(100.0).build();
        Course mapped = new Course(0, "Java", 100.0);
        Course saved = new Course(7, "Java", 100.0);
        CourseDto dto = CourseDto.builder().id(7).name("Java").build();

        when(courseMapper.toEntity(saveDto)).thenReturn(mapped);
        when(courseRepository.save(mapped)).thenReturn(saved);
        when(courseMapper.toDto(saved)).thenReturn(dto);

        CourseDto result = courseService.save(saveDto);

        assertSame(dto, result);
    }

    @Test
    void update_setsIdOnEntityBeforeSaving() {
        SaveCourseDto saveDto = SaveCourseDto.builder().courseName("Java").price(100.0).build();
        Course mapped = new Course(0, "Java", 100.0);
        Course saved = new Course(42, "Java", 100.0);
        CourseDto dto = CourseDto.builder().id(42).name("Java").build();

        when(courseMapper.toEntity(saveDto)).thenReturn(mapped);
        when(courseRepository.save(any(Course.class))).thenReturn(saved);
        when(courseMapper.toDto(saved)).thenReturn(dto);

        CourseDto result = courseService.update(saveDto, 42);

        ArgumentCaptor<Course> captor = ArgumentCaptor.forClass(Course.class);
        verify(courseRepository).save(captor.capture());
        assertEquals(42, captor.getValue().getId());
        assertSame(dto, result);
    }

    @Test
    void findById_present_returnsMappedDto() {
        Course entity = new Course(5, "Java", 100.0);
        CourseDto dto = CourseDto.builder().id(5).name("Java").build();
        when(courseRepository.findById(5)).thenReturn(Optional.of(entity));
        when(courseMapper.toDto(entity)).thenReturn(dto);

        assertSame(dto, courseService.findById(5));
    }

    @Test
    void findById_empty_mapsNull() {
        when(courseRepository.findById(99)).thenReturn(Optional.empty());
        when(courseMapper.toDto((Course) null)).thenReturn(null);

        assertNull(courseService.findById(99));
    }

    @Test
    void deleteById_delegatesToRepository() {
        courseService.deleteById(3);

        verify(courseRepository, times(1)).deleteById(3);
        verifyNoInteractions(courseMapper);
        verify(courseRepository, never()).findById(any());
    }
}
package com.example.studentportalrest.endpoint;

import com.example.studentportalrest.dto.SaveCourseDto;
import com.example.studentportalrest.model.Course;
import com.example.studentportalrest.repository.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CourseEndpointIntegrationTest extends BaseEndpointIntegrationTest {

    @Autowired
    private CourseRepository courseRepository;

    @BeforeEach
    void cleanDb() {
        courseRepository.deleteAll();
    }

    @Test
    void getAllCourses_returnsPersistedRows() throws Exception {
        courseRepository.save(Course.builder().name("Java").price(100.0).build());
        courseRepository.save(Course.builder().name("Go").price(200.0).build());

        mockMvc.perform(get("/courses").with(user("any")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void getCourseById_existing_returnsDto() throws Exception {
        Course saved = courseRepository.save(Course.builder().name("Java").price(100.0).build());

        mockMvc.perform(get("/courses/" + saved.getId()).with(user("any")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(saved.getId()))
                .andExpect(jsonPath("$.name").value("Java"))
                // currency rates are stubbed to "1" in the base class, so converted prices equal price
                .andExpect(jsonPath("$.priceUSD").value(100.0));
    }

    @Test
    void saveCourse_persistsNewRow() throws Exception {
        SaveCourseDto body = SaveCourseDto.builder().courseName("Rust").price(150.0).build();

        mockMvc.perform(post("/courses")
                        .with(user("any"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(body)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Rust"));

        assertEquals(1, courseRepository.count());
        assertTrue(courseRepository.findAll().stream().anyMatch(c -> "Rust".equals(c.getName())));
    }

    @Test
    void saveCourse_duplicateName_returnsConflict() throws Exception {
        courseRepository.save(Course.builder().name("Python").price(99.0).build());

        SaveCourseDto body = SaveCourseDto.builder().courseName("Python").price(120.0).build();

        mockMvc.perform(post("/courses")
                        .with(user("any"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(body)))
                .andExpect(status().isConflict());
    }

    @Test
    void updateCourse_changesNameAndPrice() throws Exception {
        Course saved = courseRepository.save(Course.builder().name("Old").price(1.0).build());

        SaveCourseDto body = SaveCourseDto.builder().courseName("New").price(2.0).build();

        mockMvc.perform(put("/courses/" + saved.getId())
                        .with(user("any"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(body)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(saved.getId()))
                .andExpect(jsonPath("$.name").value("New"));

        Course reloaded = courseRepository.findById(saved.getId()).orElseThrow();
        assertEquals("New", reloaded.getName());
        assertEquals(2.0, reloaded.getPrice());
    }

    @Test
    void deleteCourse_existing_returnsOkAndRemovesRow() throws Exception {
        Course saved = courseRepository.save(Course.builder().name("Scala").price(80.0).build());

        mockMvc.perform(delete("/courses/" + saved.getId()).with(user("any")))
                .andExpect(status().isOk());

        assertFalse(courseRepository.findById(saved.getId()).isPresent());
    }

    @Test
    void deleteCourse_missing_returnsNotFound() throws Exception {
        mockMvc.perform(delete("/courses/9999").with(user("any")))
                .andExpect(status().isNotFound());
    }

    @Test
    void getCourses_requiresAuthentication() throws Exception {
        mockMvc.perform(get("/courses"))
                .andExpect(status().isUnauthorized());
    }
}
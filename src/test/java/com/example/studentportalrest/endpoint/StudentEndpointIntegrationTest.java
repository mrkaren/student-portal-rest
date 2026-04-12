package com.example.studentportalrest.endpoint;

import com.example.studentportalrest.model.Course;
import com.example.studentportalrest.model.Student;
import com.example.studentportalrest.repository.CourseRepository;
import com.example.studentportalrest.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class StudentEndpointIntegrationTest extends BaseEndpointIntegrationTest {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @BeforeEach
    void cleanDb() {
        studentRepository.deleteAll();
        courseRepository.deleteAll();
    }

    @Test
    void getStudentsV1_returnsMappedDtos() throws Exception {
        Course course = courseRepository.save(Course.builder().name("Java").price(10.0).build());
        Student s = new Student();
        s.setName("Alice");
        s.setSurname("Smith");
        s.setEmail("alice@example.com");
        s.setCourse(course);
        studentRepository.save(s);

        mockMvc.perform(get("/v1/students").with(user("any")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name").value("Alice"))
                .andExpect(jsonPath("$[0].courseName").value("Java"))
                // StudentMapper fills address via AddressService (real bean)
                .andExpect(jsonPath("$[0].address").value("123 Main St, Anytown, USA"));
    }

    @Test
    void getStudentsV2_exposesSameData() throws Exception {
        Student s = new Student();
        s.setName("Bob");
        s.setSurname("Jones");
        s.setEmail("bob@example.com");
        studentRepository.save(s);

        mockMvc.perform(get("/v2/students").with(user("any")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name").value("Bob"));
    }

    @Test
    void getStudents_requiresAuthentication() throws Exception {
        mockMvc.perform(get("/v1/students"))
                .andExpect(status().isUnauthorized());
    }
}
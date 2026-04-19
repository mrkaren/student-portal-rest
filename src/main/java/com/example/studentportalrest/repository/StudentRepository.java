package com.example.studentportalrest.repository;

import com.example.studentportalrest.model.Course;
import com.example.studentportalrest.model.Skill;
import com.example.studentportalrest.model.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer>, JpaSpecificationExecutor<Student> {

    @Query("select s from Student s join s.skills sk where sk = :skill")
    List<Student> findBySkill(Skill skill);

    List<Student> findByCourse(Course course);


    @Transactional
    @Modifying
    Page<Student> findAll(Specification<Student> spec, Pageable pageable);


    void deleteAllByCourse(Course course);

}

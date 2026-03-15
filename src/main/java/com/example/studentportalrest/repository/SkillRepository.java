package com.example.studentportalrest.repository;

import com.example.studentportalrest.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill, Integer> {
}

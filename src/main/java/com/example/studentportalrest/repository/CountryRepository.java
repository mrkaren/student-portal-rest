package com.example.studentportalrest.repository;

import com.example.studentportalrest.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Integer> {

}

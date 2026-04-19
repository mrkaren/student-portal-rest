package com.example.studentportalrest.endpoint;

import com.example.studentportalrest.dto.CountryDto;
import com.example.studentportalrest.mapper.CountryMapper;
import com.example.studentportalrest.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CountryEndpoint {

    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;

    @GetMapping("/countries")
    public List<CountryDto> getCountries() {

        return countryMapper.toDto(countryRepository.findAll());
    }

}

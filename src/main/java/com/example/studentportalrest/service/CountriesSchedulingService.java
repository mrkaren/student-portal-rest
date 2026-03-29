package com.example.studentportalrest.service;

import com.example.studentportalrest.dto.CountryDto;
import com.example.studentportalrest.mapper.CountryMapper;
import com.example.studentportalrest.model.Country;
import com.example.studentportalrest.repository.CountryRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class CountriesSchedulingService {

    private final String COUNTRIES_URL = "https://list-of-all-countries-and-languages-with-their-codes.p.rapidapi.com/countries";

    private final RestTemplate restTemplate;
    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;

    public CountriesSchedulingService(@Qualifier("countryRestTemplate") RestTemplate restTemplate,
                                      CountryRepository countryRepository,
                                      CountryMapper countryMapper) {
        this.countryRepository = countryRepository;
        this.countryMapper = countryMapper;
        this.restTemplate = restTemplate;
    }

    @PostConstruct
    public void init() {
        getCountries();
    }

    @Scheduled(cron = "0 0 0 1 * *")
    public void getCountries() {
        log.info("Fetching countries");
        ResponseEntity<CountryDto[]> list = restTemplate.exchange(COUNTRIES_URL, HttpMethod.GET, null, CountryDto[].class);
        CountryDto[] body = list.getBody();
        List<Country> countries = countryMapper.toEntity(List.of(body));
        if (countries != null) {
            countryRepository.deleteAll();
            countryRepository.saveAll(countries);
        }
        log.info("Countries fetched, size: " + countries.size());
    }

}

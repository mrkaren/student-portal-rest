package com.example.studentportalrest.endpoint;

import com.example.studentportalrest.config.RestTemplateConfig;
import com.example.studentportalrest.dto.CountryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CountryEndpoint {

    @Autowired
    @Qualifier("countryRestTemplate")
    private RestTemplate restTemplate;


    @GetMapping("/countries")
    public List<CountryDto> getCountries() {

        ResponseEntity<CountryDto[]> list = restTemplate.exchange("https://list-of-all-countries-and-languages-with-their-codes.p.rapidapi.com/countries", HttpMethod.GET, null, CountryDto[].class);

        CountryDto[] body = list.getBody();
        return List.of(body);
    }

}

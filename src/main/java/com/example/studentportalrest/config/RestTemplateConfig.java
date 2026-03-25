package com.example.studentportalrest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate currencyRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public RestTemplate countryRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        restTemplate.getInterceptors().add((request, body, execution) -> {
            request.getHeaders().add("x-rapidapi-host", "list-of-all-countries-and-languages-with-their-codes.p.rapidapi.com");
            request.getHeaders().add("x-rapidapi-key", "d6f1684676mshf0cea19e6e9a00bp16268djsn8af14d6ff35f");
            return execution.execute(request, body);
        });
        return restTemplate;
    }

}

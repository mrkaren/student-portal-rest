package com.example.studentportalrest.service.currency;

import com.example.studentportalrest.dto.CurrencyDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class CurrencyService {

    @Autowired
    @Qualifier("currencyRestTemplate")
    private RestTemplate restTemplate;

    private final String CB_URL = "https://cb.am/latest.json.php?";

    @Cacheable("currency")
    public CurrencyDto getCurrency() {
        log.info("Fetching currency");
        CurrencyDto forObject = restTemplate.getForObject(CB_URL, CurrencyDto.class);
        return forObject;
    }

}

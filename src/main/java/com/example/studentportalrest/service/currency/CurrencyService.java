package com.example.studentportalrest.service.currency;

import com.example.studentportalrest.dto.CurrencyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrencyService {

    @Autowired
    @Qualifier("currencyRestTemplate")
    private RestTemplate restTemplate;

    private final String CB_URL = "https://cb.am/latest.json.php?";

    public CurrencyDto getCurrency() {
        CurrencyDto forObject = restTemplate.getForObject(CB_URL, CurrencyDto.class);
        return forObject;
    }

}

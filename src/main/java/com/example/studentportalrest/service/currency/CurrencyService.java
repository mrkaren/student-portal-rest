package com.example.studentportalrest.service.currency;

import com.example.studentportalrest.dto.CurrencyDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrencyService {

    private RestTemplate restTemplate = new RestTemplate();
    private final String CB_URL = "https://cb.am/latest.json.php";

    public CurrencyDto getCurrency() {
        return restTemplate.getForObject(CB_URL, CurrencyDto.class);
    }

}

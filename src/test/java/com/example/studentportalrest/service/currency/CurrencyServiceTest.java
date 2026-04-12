package com.example.studentportalrest.service.currency;

import com.example.studentportalrest.dto.CurrencyDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CurrencyServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private CurrencyService currencyService;

    @BeforeEach
    void setUp() {
        // The restTemplate field is @Autowired (field injection), so @InjectMocks
        // cannot always wire it without a constructor. Set it explicitly for safety.
        ReflectionTestUtils.setField(currencyService, "restTemplate", restTemplate);
    }

    @Test
    void getCurrency_returnsResultFromRestTemplate() {
        CurrencyDto expected = new CurrencyDto("400.0", "420.0", "5.0");
        when(restTemplate.getForObject("https://cb.am/latest.json.php?", CurrencyDto.class))
                .thenReturn(expected);

        CurrencyDto actual = currencyService.getCurrency();

        assertSame(expected, actual);
        verify(restTemplate).getForObject("https://cb.am/latest.json.php?", CurrencyDto.class);
    }

    @Test
    void getCurrency_nullResponse_returnsNull() {
        when(restTemplate.getForObject("https://cb.am/latest.json.php?", CurrencyDto.class))
                .thenReturn(null);

        assertNull(currencyService.getCurrency());
    }
}
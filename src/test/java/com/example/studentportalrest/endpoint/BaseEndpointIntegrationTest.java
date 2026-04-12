package com.example.studentportalrest.endpoint;

import com.example.studentportalrest.dto.CurrencyDto;
import com.example.studentportalrest.service.CountriesSchedulingService;
import com.example.studentportalrest.service.currency.CurrencyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.lenient;

/**
 * Boots the full Spring context against an in-memory H2 database (see
 * {@code src/test/resources/application-test.yaml}). Liquibase owns the schema — Hibernate
 * {@code ddl-auto=none} — so tests exercise the same migrations used in production.
 *
 * <p>Two beans touch the network at startup/usage and are replaced with mocks:
 * <ul>
 *   <li>{@link CountriesSchedulingService} — its {@code @PostConstruct} hits RapidAPI.</li>
 *   <li>{@link CurrencyService} — {@code CourseMapper} calls it on every {@code Course→CourseDto}
 *       conversion.</li>
 * </ul>
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public abstract class BaseEndpointIntegrationTest {

    @Autowired
    protected MockMvc mockMvc;

    // Instantiated directly: Spring Boot 4.0's JacksonAutoConfiguration doesn't register an
    // ObjectMapper bean under MOCK web environment here, and a fresh instance is sufficient
    // for test serialization.
    protected final ObjectMapper objectMapper = new ObjectMapper();

    @MockitoBean
    protected CountriesSchedulingService countriesSchedulingService;

    @MockitoBean
    protected CurrencyService currencyService;

    @BeforeEach
    void stubCurrencyRates() {
        // CourseMapper divides course.price by these rates. Using "1" keeps priceUSD/EUR/RUB
        // equal to price so tests don't need to hard-code conversion math.
        lenient().when(currencyService.getCurrency())
                .thenReturn(new CurrencyDto("1", "1", "1"));
    }
}
package com.example.studentportalrest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyDto {

    private String USD;
    private String EUR;
    private String RUB;

}

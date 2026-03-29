package com.example.studentportalrest.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CountryDto {


    private String country;
    private String code;

    @JsonProperty("countryName")
    public void setCountry(String country) {
        this.country = country;
    }

    @JsonProperty("alpha3")
    public void setCode(String code) {
        this.code = code;
    }

    @JsonProperty("country")
    public String getCountry() {
        return country;
    }

    @JsonProperty("code")
    public String getCode() {
        return code;
    }
}

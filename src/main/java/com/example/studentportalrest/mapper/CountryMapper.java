package com.example.studentportalrest.mapper;

import com.example.studentportalrest.dto.CountryDto;
import com.example.studentportalrest.model.Country;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CountryMapper {

    @Mapping(target = "country", source = "name")
    CountryDto toDto(Country country);

    List<CountryDto> toDto(List<Country> countries);

    List<Country> toEntity(List<CountryDto> countryDtos);

    @Mapping(target = "name", source = "country")
    Country toEntity(CountryDto countryDto);
}

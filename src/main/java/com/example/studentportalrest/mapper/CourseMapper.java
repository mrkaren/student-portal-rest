package com.example.studentportalrest.mapper;

import com.example.studentportalrest.dto.CourseDto;
import com.example.studentportalrest.dto.SaveCourseDto;
import com.example.studentportalrest.model.Course;
import com.example.studentportalrest.service.currency.CurrencyService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Mapper(componentModel = "spring", imports = {BigDecimal.class, RoundingMode.class})
public abstract class CourseMapper {

    @Autowired
    private CurrencyService currencyService;

    @Mapping(target = "priceUSD", expression = "java(new BigDecimal(String.valueOf(course.getPrice() != null ? course.getPrice() / getUSDCurrency() : 0)).setScale(3, RoundingMode.HALF_UP))")
    @Mapping(target = "priceEUR", expression = "java(new BigDecimal(String.valueOf(course.getPrice() != null ? course.getPrice() / getEURCurrency() : 0)).setScale(3, RoundingMode.HALF_UP))")
    @Mapping(target = "priceRUB", expression = "java(new BigDecimal(String.valueOf(course.getPrice() != null ? course.getPrice() / getRUBCurrency() : 0)).setScale(3, RoundingMode.HALF_UP))")
    public abstract CourseDto toDto(Course course);

    @Mapping(target = "name", source = "saveCourseDto.courseName")
    public abstract Course toEntity(SaveCourseDto saveCourseDto);

    public abstract List<CourseDto> toDtoList(List<Course> courses);

    protected Double getUSDCurrency() {
        return Double.parseDouble(currencyService.getCurrency().getUSD());
    }

    protected Double getEURCurrency() {
        return Double.parseDouble(currencyService.getCurrency().getEUR());
    }

    protected Double getRUBCurrency() {
        return Double.parseDouble(currencyService.getCurrency().getRUB());
    }

}

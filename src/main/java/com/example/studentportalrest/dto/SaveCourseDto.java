package com.example.studentportalrest.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaveCourseDto {

    @NotBlank(message = "Course name can't be blank")
    private String courseName;
    @DecimalMin(value = "0.0", inclusive = true,  message = "price should be positive")
    private Double price;

}

package com.example.studentportalrest.mapper;
import com.example.studentportalrest.dto.StudentDto;
import com.example.studentportalrest.model.Student;
import com.example.studentportalrest.service.AddressService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class StudentMapper {

    @Autowired
    private AddressService addressService;

    @Mapping(target = "courseName", source = "student.course.name")
    @Mapping(target = "address", expression = "java(mapAddress())")
    public abstract StudentDto toDto(Student student);

    public  abstract List<StudentDto> toDtoList(List<Student> students);

    protected String mapAddress() {
        return addressService.getAddress();
    }

}

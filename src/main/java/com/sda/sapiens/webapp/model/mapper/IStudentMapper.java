package com.sda.sapiens.webapp.model.mapper;

import com.sda.sapiens.webapp.model.Student;
import com.sda.sapiens.webapp.model.dto.StudentDto;
import com.sda.sapiens.webapp.model.dto.StudentEditRequest;
import org.mapstruct.*;

@Mapper
public interface IStudentMapper {

    @Mappings(value = {
            @Mapping(target = "firstName", source = "name"),
            @Mapping(target = "lastName", source = "surname"),
            @Mapping(target = "indeks", source = "studentIndex"),
            @Mapping(target = "birthDate", source = "dateOfBirth"),
    })
    public abstract Student studentEditRequestToStudent(StudentEditRequest request);

    // ------------------------------------------------
    @Mappings(value = {
            @Mapping(target = "identifier", source = "id"),
            @Mapping(target = "name", source = "firstName"),
            @Mapping(target = "surname", source = "lastName"),
            @Mapping(target = "studentIndex", source = "indeks"),
            @Mapping(target = "dateOfBirth", source = "birthDate"),
    })
    public abstract StudentDto studentToStudentDto(Student request);

    @InheritConfiguration(name = "studentEditRequestToStudent")
    void update(StudentEditRequest dto, @MappingTarget Student entity);
}

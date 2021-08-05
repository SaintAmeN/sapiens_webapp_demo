package com.sda.sapiens.webapp.services;

import com.sda.sapiens.webapp.model.Student;
import com.sda.sapiens.webapp.model.dto.StudentDto;
import com.sda.sapiens.webapp.model.dto.StudentEditRequest;
import com.sda.sapiens.webapp.model.mapper.IStudentMapper;
import com.sda.sapiens.webapp.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Stateless
public class StudentService {
    private final StudentRepository studentRepository;
    private final IStudentMapper studentMapper;

    @Inject
    public StudentService(StudentRepository studentRepository, IStudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    public Student updateStudent(Long id, StudentEditRequest request) {
        Optional<Student> studentOptional = studentRepository.getById(id);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();

            studentMapper.update(request, student);

            studentRepository.saveOrUpdate(student);

            return student;
        }

        throw new EntityNotFoundException("Entity not found, student with id: " + id);
    }

    public Student addStudent(StudentEditRequest studentInfo) {
        Student student = studentMapper.studentEditRequestToStudent(studentInfo);
        studentRepository.saveOrUpdate(student);

        return student;
    }

    public List<StudentDto> getAll() {
        return studentRepository.getAll()
                .stream()
                .map(studentMapper::studentToStudentDto)
                .collect(Collectors.toList());
    }

    public StudentDto getById(Long identifier) {
        Optional<Student> studentOptional = studentRepository.getById(identifier);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            return studentMapper.studentToStudentDto(student);
        }
        throw new EntityNotFoundException("Entity not found, student with id: " + identifier);
    }
}

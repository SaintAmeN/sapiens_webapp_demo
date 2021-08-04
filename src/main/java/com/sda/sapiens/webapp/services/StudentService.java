package com.sda.sapiens.webapp.services;

import com.sda.sapiens.webapp.model.Student;
import com.sda.sapiens.webapp.model.dto.StudentDto;
import com.sda.sapiens.webapp.model.dto.StudentEditRequest;
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

    @Inject
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student updateStudent(Long id, StudentEditRequest request) {
        Optional<Student> studentOptional = studentRepository.getById(id);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();

            if (request.getName() != null) {
                student.setFirstName(request.getName());
            }
            if (request.getSurname() != null) {
                student.setLastName(request.getSurname());
            }
            if (request.getStudentIndex() != null) {
                student.setIndeks(request.getStudentIndex());
            }
            if (request.getDateOfBirth() != null) {
                student.setBirthDate(request.getDateOfBirth());
            }

            studentRepository.saveOrUpdate(student);

            return student;
        }

        throw new EntityNotFoundException("Entity not found, student with id: " + id);
    }

    public Student addStudent(StudentEditRequest studentInfo) {
        Student student = Student.builder()
                .firstName(studentInfo.getName())
                .lastName(studentInfo.getSurname())
                .birthDate(studentInfo.getDateOfBirth())
                .indeks(studentInfo.getStudentIndex())
                .build();

        studentRepository.saveOrUpdate(student);

        return student;
    }

    public List<StudentDto> getAll() {
        return studentRepository.getAll()
                .stream()
                .map(student -> new StudentDto(
                        student.getId(),
                        student.getFirstName(),
                        student.getLastName(),
                        student.getIndeks(),
                        student.getBirthDate()))
                .collect(Collectors.toList());
    }

    public StudentDto getById(Long identifier) {
        Optional<Student> studentOptional = studentRepository.getById(identifier);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            return new StudentDto(
                    student.getId(),
                    student.getFirstName(),
                    student.getLastName(),
                    student.getIndeks(),
                    student.getBirthDate());
        }
        throw new EntityNotFoundException("Entity not found, student with id: " + identifier);
    }
}

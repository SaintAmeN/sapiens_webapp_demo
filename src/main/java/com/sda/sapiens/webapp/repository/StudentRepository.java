package com.sda.sapiens.webapp.repository;

import com.sda.sapiens.webapp.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepository {
    void saveOrUpdate(Student grade);
    List<Student> getAll();
    Optional<Student> getById(Long id);
    void delete(Long studentId);
}

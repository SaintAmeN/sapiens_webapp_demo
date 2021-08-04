package com.sda.sapiens.webapp.repository;

import com.sda.sapiens.webapp.model.Grade;
import com.sda.sapiens.webapp.model.Student;

import java.util.List;
import java.util.Optional;

public interface GradeRepository {
    void saveOrUpdate(Grade grade);
    List<Grade> getAll(Student student);
    Optional<Grade> getById(Long id);
    void delete(Long gradeId);
}

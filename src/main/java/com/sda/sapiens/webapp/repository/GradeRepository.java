package com.sda.sapiens.webapp.repository;

import com.sda.sapiens.webapp.model.Grade;

import java.util.List;
import java.util.Optional;

public interface GradeRepository {
    void saveOrUpdate(Grade grade);
    List<Grade> getAll();
    Optional<Grade> getById(Long id);
    void delete(Long gradeId);
}

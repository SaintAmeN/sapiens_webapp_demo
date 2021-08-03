package com.sda.sapiens.webapp.repository;

import com.sda.sapiens.webapp.model.Grade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Stateless
public class GradeRepositoryImpl implements GradeRepository {
    @PersistenceContext
    public EntityManager entityManager;

    @Override
    public void saveOrUpdate(Grade grade) {
        entityManager.persist(grade);
    }

    @Override
    public List<Grade> getAll() {
        return entityManager
                .createQuery("FROM Grade g", Grade.class)
                .getResultList();
    }

    @Override
    public Optional<Grade> getById(Long id) {
        return Optional.of(entityManager.find(Grade.class, id));
    }

    @Override
    public void delete(Long gradeId) {
        Optional<Grade> gradeOptional = getById(gradeId);
        if(gradeOptional.isPresent()) {
            entityManager.remove(gradeOptional.get());
        }
    }
}

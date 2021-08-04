package com.sda.sapiens.webapp.repository;

import com.sda.sapiens.webapp.model.Grade;
import com.sda.sapiens.webapp.model.Student;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Stateless
public class GradeRepositoryImpl implements GradeRepository {
    @PersistenceContext
    public EntityManager entityManager;

    @Override
    public void saveOrUpdate(Grade grade) {
        if (grade.getId() != null) {
            // jeśli id zostało dostarczone, to edytujemy istniejący rekord (merge)
            entityManager.merge(grade);
        } else {
            // jeśli nie mamy id, dodajemy nowy rekord
            entityManager.persist(grade);
        }
    }

    @Override
    public List<Grade> getAll(Student student) {
        // znajdź oceny studenta którego przekazujemy w parametrze.
        Query query = entityManager.createQuery("SELECT g FROM Grade g WHERE g.student = :studentObject");
        query.setParameter("studentObject", student);
        return query.getResultList();
    }

    @Override
    public Optional<Grade> getById(Long id) {
        return Optional.of(entityManager.find(Grade.class, id));
    }

    @Override
    public void delete(Long gradeId) {
        Optional<Grade> gradeOptional = getById(gradeId);
        if (gradeOptional.isPresent()) {
            entityManager.remove(gradeOptional.get());
        }
    }
}

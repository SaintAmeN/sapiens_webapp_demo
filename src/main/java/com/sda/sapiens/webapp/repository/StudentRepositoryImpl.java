package com.sda.sapiens.webapp.repository;

import com.sda.sapiens.webapp.model.Student;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
// 1.
// JPA -> Java Persistence API - Interfejs Extension
//                               Rozszerzenie Javy
// 2.
// JDBC - Java Database Connector - Connector (Fragment API)
//      - Connector potrzebuje Sterownika bazy danych - mysql-connector-java
// 3.
// Hibernate to implementacja JPA

@Stateless
public class StudentRepositoryImpl implements StudentRepository {
    @PersistenceContext
    public EntityManager entityManager;

    @Override
    public void saveOrUpdate(Student student) {
        entityManager.persist(student);
    }

    @Override
    public List<Student> getAll() {
        return entityManager
                .createQuery("FROM Student s", Student.class)
                .getResultList();
    }

    @Override
    public Optional<Student> getById(Long id) {
        return Optional.of(entityManager.find(Student.class, id));
    }

    @Override
    public void delete(Long studentId) {
        Optional<Student> studentOptional = getById(studentId);
        if(studentOptional.isPresent()) {
            entityManager.remove(studentOptional.get());
        }
    }
}

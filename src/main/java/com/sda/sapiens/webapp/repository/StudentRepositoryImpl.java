package com.sda.sapiens.webapp.repository;

import com.sda.sapiens.webapp.model.Student;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
        if(student.getId() != null){
            // jeśli id zostało dostarczone, to edytujemy istniejący rekord (merge)
            entityManager.merge(student);
        }else{
            // jeśli nie mamy id, dodajemy nowy rekord
            entityManager.persist(student);
        }
    }

    @Override
    public List<Student> getAll() {
        Query query = entityManager.createQuery("SELECT s from Student s", Student.class);
        return query.getResultList();
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

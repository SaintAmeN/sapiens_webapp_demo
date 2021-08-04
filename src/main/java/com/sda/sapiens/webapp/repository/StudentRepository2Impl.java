package com.sda.sapiens.webapp.repository;

import com.sda.sapiens.webapp.model.Student;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
// 1.
// JPA -> Java Persistence API - Interfejs Extension
//                               Rozszerzenie Javy
// 2.
// JDBC - Java Database Connector - Connector (Fragment API)
//      - Connector potrzebuje Sterownika bazy danych - mysql-connector-java
// 3.
// Hibernate to implementacja JPA

//@Stateful(name = "stu2")
//public class StudentRepository2Impl implements StudentRepository {
//    private final List<Student> list = new ArrayList<>();
//
//    @Override
//    public void saveOrUpdate(Student student) {
//        list.add(student);
//    }
//
//    @Override
//    public List<Student> getAll() {
//        return new ArrayList<>();
//    }
//
//    @Override
//    public Optional<Student> getById(Long id) {
//        return list.stream().filter(student -> student.getId().equals(id)).findFirst();
//    }
//
//    @Override
//    public void delete(Long studentId) {
//        list.removeIf(student -> student.getId().equals(studentId));
//    }
//}

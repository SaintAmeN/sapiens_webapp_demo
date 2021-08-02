package com.sda.sapiens.webapp.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

// POJO -
//  - pola uprywatnione
//  - gettery/settery
//  - konstruktor bezparametrowy

//@Setter
//@Getter
//@ToString
//@EqualsAndHashCode
//@RequiredArgsConstructor
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String indeks;

    // Persistance / EntityManager nie wspiera LocalDate
    private Date birthDate;

    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Grade> grades;
}

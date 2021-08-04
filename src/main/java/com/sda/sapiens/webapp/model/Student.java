package com.sda.sapiens.webapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.json.Json;
import javax.json.JsonObject;
import javax.persistence.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
    private LocalDate birthDate;

    @OneToMany(mappedBy = "student")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Grade> grades;
    
    public JsonObject toJson(){
        return Json.createObjectBuilder()
                .add("id", getId())
                .add("firstName", getFirstName())
                .add("lastName", getLastName())
                .add("indeks", getIndeks())
                .add("birthDate", new SimpleDateFormat("yyyy-MM-dd").format(getBirthDate()))
                .build();
    }
}

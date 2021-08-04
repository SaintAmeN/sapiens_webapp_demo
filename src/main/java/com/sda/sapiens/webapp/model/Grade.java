package com.sda.sapiens.webapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double gradeValue; // wartość oceny

    @Enumerated(EnumType.STRING)
    private GradeSubject subject;

    @ManyToOne()
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Student student;
    // student_id =
    // select * from grade where student_id = 5
}

package com.sda.sapiens.webapp.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentEditRequest {
    private String name;
    private String surname;
    private String studentIndex;
    private LocalDate dateOfBirth;
}

package com.alinaharkevich.mas_mp5.model;

import com.alinaharkevich.mas_mp5.validation.AtLeastOneSubclass;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@SuperBuilder
@AtLeastOneSubclass //at leas one subclass asscosiated
public abstract class Employee extends Person {
    @Min(value = 1000, message = "Salary must be at least 1000")
    private double salary;

    @NotBlank(message = "MedField is obligatory")
    @Size(min = 2, max = 100, message = "MedField should be between 2 and 100 characters")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "MedField should only contain alphabetical characters")
    private String med_field;

    @OneToOne(mappedBy = "employee_d", optional = false)
    private Doctor doctor;

    @OneToOne(mappedBy = "employee_a", optional = false)
    private Assistant assistant;

    @OneToOne(mappedBy = "employee_i", optional = false)
    private Intern intern;
}

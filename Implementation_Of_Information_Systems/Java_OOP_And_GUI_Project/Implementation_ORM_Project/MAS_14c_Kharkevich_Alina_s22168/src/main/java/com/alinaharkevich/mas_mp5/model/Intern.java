package com.alinaharkevich.mas_mp5.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@SuperBuilder
public class Intern extends Employee{
    @NotBlank(message = "Year is obligatory")
    @Pattern(regexp = "^(second|first)$", message = "Year should be either 'treatment' or 'operation'")
    private String internship_year;

    @OneToOne(optional = false)
    @JoinColumn(name = "id", nullable = false, updatable = false, unique = true)
    private Employee employee_i;
}

package com.alinaharkevich.mas_mp5.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
public class Assistant extends Employee {

    @NotBlank(message = "Specialization is obligatory")
    @Pattern(regexp = "^(treatment|operation)$", message = "Specialization should be either 'treatment' or 'operation'")
    private String specialization;

    @OneToOne(optional = false)
    @JoinColumn(name = "id", nullable = false, updatable = false, unique = true)
    private Employee employee_a;

}

package com.alinaharkevich.mas_mp5.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

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

public class Child extends Patient {
    @NotBlank(message = "Parent phone is obligatory")
    @Size(min = 12, max = 12, message = "Parent phone should be 12 characters long")
    @Pattern(regexp = "^\\+[0-9]{11}$", message = "Parent phone should start with '+' and have 11 following digits")
    private String parent_phone;

    @OneToOne(optional = false)
    @JoinColumn(name = "id", nullable = false, updatable = false, unique = true)
    private Patient patient;
}

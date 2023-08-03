package com.alinaharkevich.mas_mp5.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@SuperBuilder
public class Adult extends Patient{

    @Min(0)
    @Max(50)
    private int discount;

    @OneToOne(optional = false)
    @JoinColumn(name = "id", nullable = false, updatable = false, unique = true)
    private Patient patient;

    @OneToMany(mappedBy = "adult", cascade = {CascadeType.REMOVE})
    @NotEmpty(message = "At least one reservation is required")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Reservation> reservations = new HashSet<>();
}

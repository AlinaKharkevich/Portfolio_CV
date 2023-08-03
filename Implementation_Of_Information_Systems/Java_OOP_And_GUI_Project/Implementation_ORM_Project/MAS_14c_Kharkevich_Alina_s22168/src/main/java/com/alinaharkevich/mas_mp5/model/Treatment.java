package com.alinaharkevich.mas_mp5.model;

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
public class Treatment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    private Long treatment_id;

    @NotBlank(message = "Description is obligatory")
    @Size(min = 3, max = 500, message = "Description should be between 3 and 1000 characters")
    private String description;

    @ManyToMany(mappedBy = "treatments")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Appointment> appointments = new HashSet<>();
}
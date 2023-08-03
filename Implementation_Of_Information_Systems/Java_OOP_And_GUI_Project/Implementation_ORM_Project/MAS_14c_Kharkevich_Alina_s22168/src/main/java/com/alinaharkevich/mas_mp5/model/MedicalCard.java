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
public class MedicalCard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long card_id;

    @Size(min = 3, max = 1000, message = "Description should be between 3 and 1000 characters")
    private String description;

    @OneToOne(optional = false)
    @JoinColumn(name = "id", nullable = false, updatable = false,  unique = true)
    private Patient owner;

    @ManyToOne
    @JoinColumn(name = "storage_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Storage storage;
}
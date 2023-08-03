package com.alinaharkevich.mas_mp5.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import java.util.HashMap;
import java.util.Map;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@SuperBuilder
public class Storage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    private Long storage_id;

    @NotBlank(message = "Type is obligatory")
    @Size(min = 4, max = 10, message = "Type should be between 4 and 10 characters")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Type should only contain alphabetical characters")
    private String type;

    @Min(100)
    private Double capacity;

    @OneToMany(mappedBy = "storage", cascade = CascadeType.ALL,  orphanRemoval = true)
    @MapKey(name = "card_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Map<Long, MedicalCard> medicalCards = new HashMap<>();

}

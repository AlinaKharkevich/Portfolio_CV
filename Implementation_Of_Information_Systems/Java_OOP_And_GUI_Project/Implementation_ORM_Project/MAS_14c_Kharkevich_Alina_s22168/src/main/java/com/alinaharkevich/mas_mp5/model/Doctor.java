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
public class Doctor extends Employee {

    @Min(100)
    @Max(350)
    private int room_num;

    @NotBlank(message = "Department is obligatory")
    @Size(min = 3, max = 10, message = "Department should be between 4 and 10 characters")
    private String department;

    @ElementCollection
    @CollectionTable(name = "Doctor_Language", joinColumns = @JoinColumn(name = "doctor_id"))
    @Column(name = "language")
    @NotEmpty(message = "Languages must have at least one value")
    @Builder.Default
    private Set<String> languages = new HashSet<>();

    @OneToOne(optional = false)
    @JoinColumn(name = "id", nullable = false, updatable = false, unique = true)
    private Employee employee_d;

    @ManyToMany(mappedBy = "doctors")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Appointment> appointments = new HashSet<>();

}

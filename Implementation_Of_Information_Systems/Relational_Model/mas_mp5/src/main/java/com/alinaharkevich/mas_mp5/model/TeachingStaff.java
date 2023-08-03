package com.alinaharkevich.mas_mp5.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@SuperBuilder
public abstract class TeachingStaff {

    @NotBlank(message = "Name is obligatory") //FOR STRING
    @Size(min=2, max=100) //size of string
    private String name;
    @Min(1000)
    private double salary;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="faculty_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Faculty teachIn;
}

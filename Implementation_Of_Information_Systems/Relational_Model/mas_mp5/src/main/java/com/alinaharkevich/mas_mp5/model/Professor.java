package com.alinaharkevich.mas_mp5.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.NotBlank;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Professor extends TeachingStaff{
    @NotBlank(message = "Mode is obligatory") //FOR STRING
    @Size(min=2, max=7) //size of string
    private String preferable_mode; //Ex. online/offline/Hibride

    @ElementCollection
    @CollectionTable(name="language", joinColumns = @JoinColumn(name="professor_id"))
    @Builder.Default
    private Set<String> languages = new HashSet<>(); //languages to conduct the lesson

    @OneToMany(mappedBy = "professor", cascade = {CascadeType.REMOVE})
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Conduction> conductions;
}

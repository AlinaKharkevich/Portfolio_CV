package com.alinaharkevich.mas_mp5.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.NotBlank;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Name is obligatory") //FOR STRING
    @Size(min=2, max=100) //size of string
    private String name;

    @Min(1)
    private int office;
    @Min(1)
    private int students_num; //Number of students on faculty

    @OneToMany(mappedBy = "teachIn", fetch = FetchType.LAZY)
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<TeachingStaff> teachingStaff = new HashSet<>();

}

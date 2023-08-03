package com.alinaharkevich.mas_mp5.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"professor_id", "lesson_id"})
})
public class Conduction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "professor_id", nullable = false)
    @NotNull
    private Professor professor;
    @ManyToOne
    @JoinColumn(name = "lesson_id", nullable = false)
    @NotNull
    private Lesson lesson;

    @NotNull
    private LocalTime startTime;

    private LocalTime endTime; //optional
}

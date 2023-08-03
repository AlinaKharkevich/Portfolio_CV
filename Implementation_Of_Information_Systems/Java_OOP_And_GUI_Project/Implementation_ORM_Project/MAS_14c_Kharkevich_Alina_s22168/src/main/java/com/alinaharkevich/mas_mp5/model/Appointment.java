package com.alinaharkevich.mas_mp5.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@SuperBuilder
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    private Long app_id;

    @NotBlank(message = "Status is obligatory")
    @Pattern(regexp = "^(registered|completed|cancelled by customer)$",
            message = "Invalid status")
    private String status;

    @Size(min = 3, max = 500, message = "Description should be between 3 and 1000 characters")
    private String description_disease;

    @Size(min = 2, max = 10, message = "Note should be between 2 and 10 characters")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Note should only contain alphabetical characters")
    private String note; //optional

    @NotNull(message = "Date is obligatory")
    @Past(message = "Date should be in the past")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date appointment_date;

    @ManyToMany
    @JoinTable(
            name = "appointment_doctor",
            joinColumns = @JoinColumn(name = "app_id"),
            inverseJoinColumns = @JoinColumn(name = "doctor_id")
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @NotEmpty(message = "At least one doctor must be assigned to the appointment.")
    private Set<Doctor> doctors = new HashSet<>();

    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(
            name = "appointment_treatment",
            joinColumns = @JoinColumn(name = "app_id"),
            inverseJoinColumns = @JoinColumn(name = "treatment_id")
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Treatment> treatments = new HashSet<>();

    @OneToOne(mappedBy = "appointment", cascade = {CascadeType.REMOVE})
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Reservation reservations;
}

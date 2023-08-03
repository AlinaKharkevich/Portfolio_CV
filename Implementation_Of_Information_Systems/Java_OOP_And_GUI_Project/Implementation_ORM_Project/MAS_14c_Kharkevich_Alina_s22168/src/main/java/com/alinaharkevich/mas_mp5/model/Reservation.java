package com.alinaharkevich.mas_mp5.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id", "app_id"})
})
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long reservation_id;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull(message = "Reservation date is obligatory")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date reservation_date;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull(message = "Payment date is obligatory")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date payment_date;

    @NotBlank(message = "Payment Method is obligatory")
    @Pattern(regexp = "^(card|cash)$", message = "Year should be either 'card' or 'cash'")
    private String payment_method;

    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    @NotNull
    private Adult adult;

    @OneToOne
    @JoinColumn(name = "app_id", nullable = false)
    @NotNull
    private Appointment appointment;

}

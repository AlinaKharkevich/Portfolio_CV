package com.alinaharkevich.mas_mp5.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@SuperBuilder

public abstract class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    private Long id;

    @NotBlank(message = "Name is obligatory")
    @Size(min = 2, max = 100, message = "Name should be between 2 and 100 characters")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Name should only contain alphabetical characters")
    private String name;

    @NotBlank(message = "Surname is obligatory")
    @Size(min = 2, max = 100, message = "Surname should be between 2 and 100 characters")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Surname should only contain alphabetical characters")
    private String surname;

    @NotBlank(message = "Phone is obligatory")
    @Size(min = 12, max = 12, message = "Phone should be 12 characters long")
    @Pattern(regexp = "^\\+[0-9]{11}$", message = "Phone should start with '+' and have 11 following digits")
    private String phone;

    @NotNull(message = "Birth date is obligatory")
    @Past(message = "Birth date should be in the past")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth_date;

    protected Date getDateOfBirth() {
        return birth_date;
    }
}

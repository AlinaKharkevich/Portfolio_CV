package com.alinaharkevich.mas_mp5.model;

import com.alinaharkevich.mas_mp5.validation.AtLeastOneSubclassAndAge;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@SuperBuilder
@AtLeastOneSubclassAndAge

public abstract class Patient extends Person {
    @NotBlank(message = "NIP is obligatory")
    @Size(min = 13, max = 13, message = "NIP should be a 10-digit number")
    @Pattern(regexp =  "[1-2]{3}-[0-9]{2}-[0-9]{2}-[0-9]{3}", message = "Invalid NIP format")
    @Column(unique = true)
    private String nip;

    public Date getDateOfBirth() {
        return super.getDateOfBirth();
    }

    @OneToOne(mappedBy = "patient", optional = false)
    private Child child;

    @OneToOne(mappedBy = "patient", optional = false)
    private Adult adult;

    @OneToOne(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private MedicalCard medicalCard;

}
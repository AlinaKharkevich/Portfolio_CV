package com.alinaharkevich.mas_mp5.model;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Postgraduate  extends TeachingStaff{
    @NotBlank(message = "Supervisor is obligatory") //FOR STRING
    @Size(min=2, max=100) //size of string
    private String supervisor;
}

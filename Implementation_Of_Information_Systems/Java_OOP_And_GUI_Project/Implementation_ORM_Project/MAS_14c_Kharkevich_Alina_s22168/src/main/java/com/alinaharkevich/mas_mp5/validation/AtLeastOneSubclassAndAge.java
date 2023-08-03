package com.alinaharkevich.mas_mp5.validation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AtLeastOneSubclassAndAgeValidator.class)
public @interface AtLeastOneSubclassAndAge {
    String message() default "At least one subclass must exist, and child must be below 18 years old";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

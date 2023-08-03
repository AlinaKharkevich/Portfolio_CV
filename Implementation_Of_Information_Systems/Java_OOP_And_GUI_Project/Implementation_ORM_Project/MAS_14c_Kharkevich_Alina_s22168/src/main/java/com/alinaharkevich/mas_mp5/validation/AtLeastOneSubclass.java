package com.alinaharkevich.mas_mp5.validation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AtLeastOneSubclassValidator.class)
public @interface AtLeastOneSubclass {
    String message() default "At least one subclass must exist";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

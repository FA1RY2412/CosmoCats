package org.example.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CosmicWordValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface CosmicWordCheck {
  String message() default "name must contain a cosmic term (e.g., star, galaxy, comet, nebula)";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}

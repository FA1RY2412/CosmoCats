package org.example.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Set;

public class CosmicWordValidator implements ConstraintValidator<CosmicWordCheck, String> {
  private static final Set<String> WORDS = Set.of(
      "star","galaxy","comet","nebula","cosmic","astro","meteor","orbit","planet","saturn","mars","venus"
  );

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (value == null) return true;
    String lower = value.toLowerCase();
    return WORDS.stream().anyMatch(lower::contains);
  }
}

package pl.zdzimi.shop.model.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ZipCodeValidator implements ConstraintValidator<ZipCode, String> {

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    return value.matches("\\d{2}-\\d{3}");
  }

}

package pl.zdzimi.shop.model.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, String> {

  @Override
  public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
    if (value.equals("")) {
      return true;
    }
    if (value.length() < 8) {
      return false;
    }
    boolean containsSmallLetter = false;
    boolean containsBigLetter = false;
    boolean containsDigit = false;
    for (int i = 0; i < value.length(); i++) {
      if (SignSolver.isSmallLetter(value.charAt(i))) {
        containsSmallLetter = true;
      } else if (SignSolver.isBigLetter(value.charAt(i))) {
        containsBigLetter = true;
      } else if (SignSolver.isDigit(value.charAt(i))) {
        containsDigit = true;
      }
    }
    return containsSmallLetter && containsBigLetter && containsDigit;
  }

}

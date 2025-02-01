package pl.zdzimi.shop.model.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import pl.zdzimi.shop.repository.UsersRepository;

@RequiredArgsConstructor
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

  private final UsersRepository usersRepository;

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    return usersRepository.findByEmail(value).isEmpty();
  }

}

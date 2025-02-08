package pl.zdzimi.shop.model.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import pl.zdzimi.shop.repository.CategoriesRepository;

@RequiredArgsConstructor
public class UniqueCategoryValidator implements ConstraintValidator<UniqueCategory, String> {

  private final CategoriesRepository categoriesRepository;
  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    return categoriesRepository.findByName(value).isEmpty();
  }

}

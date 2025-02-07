package pl.zdzimi.shop.model.validation;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ZipCodeValidatorTest {

  private final ZipCodeValidator zipCodeValidator = new ZipCodeValidator();
  @Mock
  private ConstraintValidatorContext context;

  @ParameterizedTest(name = "{index} - {arguments} - should be valid.")
  @ValueSource(strings = {"00-000", "01-001", "99-999", "12-852"})
  void shouldBeValid(String password) {
    assertThat(zipCodeValidator.isValid(password, context)).isTrue();
  }

  @ParameterizedTest(name = "{index} - {arguments} - shouldn't be valid.")
  @ValueSource(strings = {"1-111", "11-11", "12_123", "5-3", "ab-bew", "34 964"})
  void shouldNotBeValid(String password) {
    assertThat(zipCodeValidator.isValid(password, context)).isFalse();
  }

}

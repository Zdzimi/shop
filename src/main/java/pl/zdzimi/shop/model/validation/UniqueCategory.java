package pl.zdzimi.shop.model.validation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = UniqueCategoryValidator.class)
@Documented
public @interface UniqueCategory {

  String message() default "Kategoria już istnieje";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

}

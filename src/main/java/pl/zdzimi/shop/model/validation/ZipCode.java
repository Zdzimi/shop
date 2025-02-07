package pl.zdzimi.shop.model.validation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = ZipCodeValidator.class)
public @interface ZipCode {

  String message() default "Kod pocztowy musi mieć format XX-XXX";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

}

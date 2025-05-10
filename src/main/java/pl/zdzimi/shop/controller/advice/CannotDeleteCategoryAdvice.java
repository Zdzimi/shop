package pl.zdzimi.shop.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.zdzimi.shop.service.exception.CannotDeleteCategory;

@ControllerAdvice
public class CannotDeleteCategoryAdvice {

  @ExceptionHandler(CannotDeleteCategory.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  public String mailExceptionHandler(CannotDeleteCategory e, Model model) {
    model.addAttribute("exception", e.getMessage());
    return "exceptionInfo";
  }

}

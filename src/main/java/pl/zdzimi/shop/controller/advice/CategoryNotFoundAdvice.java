package pl.zdzimi.shop.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.zdzimi.shop.service.exception.CategoryNotFoundException;

@ControllerAdvice
public class CategoryNotFoundAdvice {

  @ExceptionHandler(CategoryNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public String userNotFoundHandler(CategoryNotFoundException e, Model model) {
    model.addAttribute("exception", e.getMessage());
    return "exceptionInfo";
  }

}

package pl.zdzimi.shop.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.zdzimi.shop.service.exception.InsufficientQuantityException;

@ControllerAdvice
public class InsufficientQuantityAdvice {

  @ExceptionHandler(InsufficientQuantityException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public String mailExceptionHandler(InsufficientQuantityException e, Model model) {
    model.addAttribute("exception", e.getMessage());
    return "exceptionInfo";
  }

}

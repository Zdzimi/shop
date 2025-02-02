package pl.zdzimi.shop.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.zdzimi.shop.service.exception.CodeNotFoundException;

@ControllerAdvice
public class CodeNotFoundAdvice {

  @ExceptionHandler(CodeNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public String codeNotFoundHandler(CodeNotFoundException e, Model model) {
    model.addAttribute("exception", e.getMessage());
    return "exceptionInfo";
  }

}

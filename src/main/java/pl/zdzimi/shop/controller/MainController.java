package pl.zdzimi.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/shop")
public class MainController {

  @GetMapping
  public String getHomePage() {
    return "home";
  }

}

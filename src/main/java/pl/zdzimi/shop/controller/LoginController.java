package pl.zdzimi.shop.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.zdzimi.shop.model.SingingUser;
import pl.zdzimi.shop.securiry.PrincipalUser;
import pl.zdzimi.shop.service.UsersService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/shop")
public class LoginController {

  private final UsersService usersService;

  @GetMapping("/registration")
  public String getRegistrationPage(@AuthenticationPrincipal PrincipalUser user, Model model) {
    if (isLoggedIn(user)) {
      return "redirect:/shop/account";
    }
    model.addAttribute("singingUser", new SingingUser());
    return "registration";
  }

  @PostMapping("/registration")
  public String register(@Valid @ModelAttribute SingingUser singingUser, BindingResult result) {
    if (result.hasErrors()) {
      return "registration";
    }
    usersService.createCustomer(singingUser);
    return "redirect:/shop/login";
  }

  @GetMapping("/login")
  public String getLoginPage(@AuthenticationPrincipal PrincipalUser user) {
    if (isLoggedIn(user)) {
      return "redirect:/shop/account";
    }
    return "login";
  }

  private boolean isLoggedIn(PrincipalUser user) {
    return user != null;
  }

}

package pl.zdzimi.shop.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.zdzimi.shop.model.Pass;
import pl.zdzimi.shop.model.SingingUser;
import pl.zdzimi.shop.securiry.PrincipalUser;
import pl.zdzimi.shop.service.PasswordRecoveryService;
import pl.zdzimi.shop.service.UsersService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/shop")
public class LoginController {

  private final UsersService usersService;
  private final PasswordRecoveryService passwordRecoveryService;

  @GetMapping("/registration")
  public String getRegistrationPage(@AuthenticationPrincipal PrincipalUser user, Model model) {
    if (isLoggedIn(user)) {
      return "redirect:/shop/account";
    }
    model.addAttribute("singingUser", new SingingUser());
    return "registration";
  }

  @PostMapping("/registration")
  public String register(@Valid @ModelAttribute SingingUser singingUser, BindingResult result,
      @AuthenticationPrincipal PrincipalUser user) {
    if (isLoggedIn(user)) {
      return "redirect:/shop/account";
    }
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

  @GetMapping("/password-reset")
  public String passwordResetView(@AuthenticationPrincipal PrincipalUser user) {
    if (isLoggedIn(user)) {
      return "redirect:/shop/account";
    }
    return "passwordReset";
  }

  @PostMapping("/password-reset")
  public String passwordReset(@AuthenticationPrincipal PrincipalUser user,
      @RequestParam String username, Model model) {
    if (isLoggedIn(user)) {
      return "redirect:/shop/account";
    }
    model.addAttribute("email", passwordRecoveryService.proceedForgotPasswordRequest(username));
    return "linkSentInfo";
  }

  @GetMapping("/password-reset/{code}")
  public String changePasswordView(@PathVariable String code,
      @AuthenticationPrincipal PrincipalUser user, Model model) {
    if (isLoggedIn(user)) {
      return "redirect:/shop/account";
    }
    if (passwordRecoveryService.isNotExpired(code)) {
      model.addAttribute("code", code);
      model.addAttribute("pass", new Pass());
      return "changePassword";
    } else {
      return "codeExpiredInfo";
    }
  }

  @PostMapping("/password-reset/{code}")
  public String changePassword(@Valid @ModelAttribute Pass pass, BindingResult result,
      @AuthenticationPrincipal PrincipalUser user, @PathVariable String code, Model model) {
    if (isLoggedIn(user)) {
      return "redirect:/shop/account";
    }
    if (result.hasErrors()) {
      model.addAttribute("code", code);
      return "changePassword";
    }
    if (passwordRecoveryService.isNotExpired(code)) {
      passwordRecoveryService.changePassword(code, pass.getPassword());
      return "passwordChangedInfo";
    } else {
      return "codeExpiredInfo";
    }
  }

  private boolean isLoggedIn(PrincipalUser user) {
    return user != null;
  }

}

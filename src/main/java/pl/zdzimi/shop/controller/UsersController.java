package pl.zdzimi.shop.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.zdzimi.shop.model.AddressDTO;
import pl.zdzimi.shop.model.SearchingParams;
import pl.zdzimi.shop.securiry.PrincipalUser;
import pl.zdzimi.shop.service.AddressesService;
import pl.zdzimi.shop.service.CategoriesService;
import pl.zdzimi.shop.service.OrdersService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/shop/account")
public class UsersController {

  private final AddressesService addressesService;
  private final OrdersService ordersService;
  private final CategoriesService categoriesService;

  @GetMapping
  public String getAccountPage(@AuthenticationPrincipal PrincipalUser principalUser, Model model) {
    model.addAttribute("user", principalUser.getUser());
    model.addAttribute("addresses", addressesService.findAddress(principalUser.getUser()));
    model.addAttribute("addressDTO", new AddressDTO());
    model.addAttribute("categories", categoriesService.getCategories());
    model.addAttribute("searchingParams", new SearchingParams());
    return "account";
  }

  @PostMapping("/address")
  public String saveAddress(@Valid @ModelAttribute AddressDTO addressDTO, BindingResult result, Model model, @AuthenticationPrincipal PrincipalUser principalUser) {
    if (result.hasErrors()) {
      model.addAttribute("user", principalUser.getUser());
      model.addAttribute("addresses", addressesService.findAddress(principalUser.getUser()));
      return "account";
    }
    addressesService.save(addressDTO, principalUser.getUser());
    return "redirect:/shop/account";
  }

  @GetMapping("/address/{id}")
  public String delete(@AuthenticationPrincipal PrincipalUser principalUser, @PathVariable Long id) {
    addressesService.delete(principalUser.getUser(), id);
    return "redirect:/shop/account";
  }

  @GetMapping("/history")
  public String getMyOrdersView(Model model, @AuthenticationPrincipal PrincipalUser principalUser) {
    model.addAttribute("orders", ordersService.getAllMyOrders(principalUser.getUser()));
    model.addAttribute("categories", categoriesService.getCategories());
    model.addAttribute("searchingParams", new SearchingParams());
    return "orders";
  }

}

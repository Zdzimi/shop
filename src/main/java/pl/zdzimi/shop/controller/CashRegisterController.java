package pl.zdzimi.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.zdzimi.shop.model.Bill;
import pl.zdzimi.shop.model.SearchingParams;
import pl.zdzimi.shop.securiry.PrincipalUser;
import pl.zdzimi.shop.service.AddressesService;
import pl.zdzimi.shop.service.CategoriesService;
import pl.zdzimi.shop.service.OrdersService;
import pl.zdzimi.shop.service.StoreService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/shop/cash-register")
public class CashRegisterController {

  private final StoreService storeService;
  private final AddressesService addressesService;
  private final OrdersService ordersService;
  private final CategoriesService categoriesService;

  @GetMapping
  public String getShoppingCart(Model model, @AuthenticationPrincipal PrincipalUser principalUser) {
    model.addAttribute("bill", new Bill(storeService.getShoppingCart()));
    model.addAttribute("addresses", addressesService.findAddress(principalUser.getUser()));
    model.addAttribute("categories", categoriesService.getCategories());
    model.addAttribute("searchingParams", new SearchingParams());
    return "cashRegister";
  }

  @PostMapping
  public String order(@AuthenticationPrincipal PrincipalUser principalUser,
      @RequestParam Long addressId, Model model) {
    ordersService.order(principalUser.getUser(), addressId);
    model.addAttribute("categories", categoriesService.getCategories());
    model.addAttribute("searchingParams", new SearchingParams());
    return "orderedInfo";
  }

}

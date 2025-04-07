package pl.zdzimi.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.zdzimi.shop.model.Bill;
import pl.zdzimi.shop.securiry.PrincipalUser;
import pl.zdzimi.shop.service.AddressesService;
import pl.zdzimi.shop.service.OrdersService;
import pl.zdzimi.shop.service.StoreService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/shop/cash-register")
public class CashRegisterController {

  private final StoreService storeService;
  private final AddressesService addressesService;
  private final OrdersService ordersService;

  @GetMapping
  public String getShoppingCart(Model model, @AuthenticationPrincipal PrincipalUser principalUser) {
    model.addAttribute("bill", new Bill(storeService.getShoppingCart()));
    model.addAttribute("addresses", addressesService.findAddress(principalUser.getUser()));
    return "cashRegister";
  }

  @PostMapping
  public String order(@AuthenticationPrincipal PrincipalUser principalUser, @RequestParam Long addressId) {
    ordersService.order(principalUser.getUser(), addressId);
    return "orderedInfo";
  }

}

package pl.zdzimi.shop.controller;

import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.zdzimi.shop.model.Bill;
import pl.zdzimi.shop.model.SearchingParams;
import pl.zdzimi.shop.service.CategoriesService;
import pl.zdzimi.shop.service.StoreService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/shop/shopping-cart")
public class ShoppingCartController {

  private final StoreService storeService;
  private final CategoriesService categoriesService;

  @GetMapping
  public String getShoppingCart(Model model) {
    model.addAttribute("categories", categoriesService.getCategories());
    model.addAttribute("searchingParams", new SearchingParams());
    model.addAttribute("bill", new Bill(storeService.getShoppingCart()));
    return "shoppingCart";
  }

  @GetMapping("/{id}")
  public String remove(@PathVariable Long id) {
    storeService.remove(id);
    return "redirect:/shop/shopping-cart";
  }

  @PostMapping("/{id}")
  public String update(@Min(value = 1, message = "min = 1") @RequestParam int amount, @PathVariable Long id) {
    storeService.update(id, amount);
    return "redirect:/shop/shopping-cart";
  }

}

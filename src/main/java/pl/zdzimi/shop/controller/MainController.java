package pl.zdzimi.shop.controller;

import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.zdzimi.shop.model.SearchingParams;
import pl.zdzimi.shop.service.CategoriesService;
import pl.zdzimi.shop.service.CommoditiesService;
import pl.zdzimi.shop.service.StoreService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/shop")
public class MainController {

  private final CommoditiesService commoditiesService;
  private final CategoriesService categoriesService;
  private final StoreService storeService;

  @GetMapping
  public String getHomePage(Model model) {
    model.addAttribute("categories", categoriesService.getCategories());
    model.addAttribute("searchingParams", new SearchingParams());
    model.addAttribute("commodities", commoditiesService.findAllAvailable());
    return "home";
  }

  @PostMapping
  public String getCommoditiesBySearchingParams(Model model,
      @ModelAttribute SearchingParams searchingParams) {
    model.addAttribute("categories", categoriesService.getCategories());
    model.addAttribute("searchingParams", new SearchingParams());
    model.addAttribute("commodities",
        commoditiesService.findAllAvailableBySearchingParams(searchingParams));
    return "home";
  }

  @GetMapping("/product/{id}")
  public String getCommodityDetails(@PathVariable Long id, Model model) {
    model.addAttribute("categories", categoriesService.getCategories());
    model.addAttribute("searchingParams", new SearchingParams());
    model.addAttribute("commodity", commoditiesService.findById(id));
    return "commodityDetails";
  }

  @PostMapping("/product/{id}")
  public String addToShippingCart(@Min(value = 1, message = "min = 1") @RequestParam int amount,
      @PathVariable Long id, Model model) {
    model.addAttribute("categories", categoriesService.getCategories());
    model.addAttribute("searchingParams", new SearchingParams());
    storeService.add(id, amount);
    return "productAddedInfo";
  }

}

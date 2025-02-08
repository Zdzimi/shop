package pl.zdzimi.shop.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.zdzimi.shop.model.CategoryDTO;
import pl.zdzimi.shop.service.CategoriesService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/shop/admin")
public class AdminController {

  private final CategoriesService categoriesService;

  @GetMapping("/categories")
  public String getAdminCategoriesView(Model model) {
    model.addAttribute("categoryDTO", new CategoryDTO());
    model.addAttribute("categories", categoriesService.getAll());
    return "adminCategories";
  }

  @PostMapping("/categories")
  public String createCategory(@Valid @ModelAttribute CategoryDTO categoryDTO, BindingResult result, Model model) {
    if (result.hasErrors()) {
      model.addAttribute("categories", categoriesService.getAll());
      return "adminCategories";
    }
    categoriesService.create(categoryDTO);
    return "redirect:/shop/admin/categories";
  }

  @GetMapping("/categories/{id}")
  public String delete(@PathVariable Long id) {
    categoriesService.delete(id);
    return "redirect:/shop/admin/categories";
  }

}

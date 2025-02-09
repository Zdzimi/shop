package pl.zdzimi.shop.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.zdzimi.shop.model.CategoryDTO;
import pl.zdzimi.shop.model.EmployeeDTO;
import pl.zdzimi.shop.service.CategoriesService;
import pl.zdzimi.shop.service.EmployeesService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/shop/admin")
public class AdminController {

  private final CategoriesService categoriesService;
  private final EmployeesService employeesService;

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

  @GetMapping("/employees")
  public String getEmployeesView(Model model) {
    model.addAttribute("employeeDTO", new EmployeeDTO());
    model.addAttribute("employees", employeesService.findAll());
    return "employees";
  }

  @PostMapping("/employees")
  public String createEmployee(@Valid @ModelAttribute EmployeeDTO employeeDTO, BindingResult result, Model model) {
    if (result.hasErrors()) {
      model.addAttribute("employees", employeesService.findAll());
      return "employees";
    }
    employeesService.create(employeeDTO);
    return "redirect:/shop/admin/employees";
  }

  @GetMapping("/employees/{id}")
  public String deleteEmployee(@PathVariable Long id) {
    employeesService.delete(id);
    return "redirect:/shop/admin/employees";
  }

}

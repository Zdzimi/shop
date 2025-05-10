package pl.zdzimi.shop.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.zdzimi.shop.model.CategoryDTO;
import pl.zdzimi.shop.model.CreatingCommodity;
import pl.zdzimi.shop.model.EmployeeDTO;
import pl.zdzimi.shop.model.SearchingParams;
import pl.zdzimi.shop.service.AmountsService;
import pl.zdzimi.shop.service.CategoriesService;
import pl.zdzimi.shop.service.CommoditiesService;
import pl.zdzimi.shop.service.EmployeesService;
import pl.zdzimi.shop.service.OrdersService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/shop/admin")
public class AdminController {

  private final CategoriesService categoriesService;
  private final EmployeesService employeesService;
  private final CommoditiesService commoditiesService;
  private final AmountsService amountsService;
  private final OrdersService ordersService;

  @GetMapping("/categories")
  public String getAdminCategoriesView(Model model) {
    model.addAttribute("categoryDTO", new CategoryDTO());
    model.addAttribute("categoriesAll", categoriesService.getAll());
    model.addAttribute("categories", categoriesService.getCategories());
    model.addAttribute("searchingParams", new SearchingParams());
    return "adminCategories";
  }

  @PostMapping("/categories")
  public String createCategory(@Valid @ModelAttribute CategoryDTO categoryDTO, BindingResult result, Model model) {
    if (result.hasErrors()) {
      model.addAttribute("categoriesAll", categoriesService.getAll());
      model.addAttribute("categories", categoriesService.getCategories());
      model.addAttribute("searchingParams", new SearchingParams());
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
    model.addAttribute("categories", categoriesService.getCategories());
    model.addAttribute("searchingParams", new SearchingParams());
    return "employees";
  }

  @PostMapping("/employees")
  public String createEmployee(@Valid @ModelAttribute EmployeeDTO employeeDTO, BindingResult result, Model model) {
    if (result.hasErrors()) {
      model.addAttribute("employees", employeesService.findAll());
      model.addAttribute("categories", categoriesService.getCategories());
      model.addAttribute("searchingParams", new SearchingParams());
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

  @GetMapping("/commodities")
  public String getCommoditiesView(Model model) {
    model.addAttribute("commodities", commoditiesService.findAll());
    model.addAttribute("creatingCommodity", new CreatingCommodity());
    model.addAttribute("categoriesAll", categoriesService.getAll());
    model.addAttribute("categories", categoriesService.getCategories());
    model.addAttribute("searchingParams", new SearchingParams());
    return "commodities";
  }

  @PostMapping("/commodities")
  public String addCommodity(@Valid @ModelAttribute CreatingCommodity creatingCommodity, BindingResult result, Model model) {
    if (result.hasErrors()) {
      model.addAttribute("commodities", commoditiesService.findAll());
      model.addAttribute("categoriesAll", categoriesService.getAll());
      model.addAttribute("categories", categoriesService.getCategories());
      model.addAttribute("searchingParams", new SearchingParams());
      return "commodities";
    }
    commoditiesService.saveCommodity(creatingCommodity);
    return "redirect:/shop/admin/commodities";
  }

  @GetMapping("/commodities/{id}")
  public String deleteCommodity(@PathVariable Long id) {
    amountsService.setAmount(id, 0);
    return "redirect:/shop/admin/commodities";
  }

  @PostMapping("/commodities/{id}")
  public String addIncreaseAmount(@Min(1) @RequestParam int amount, @PathVariable Long id) {
    amountsService.setAmount(id, amount);
    return "redirect:/shop/admin/commodities";
  }

  @GetMapping("/orders")
  public String getOrdersView(Model model) {
    model.addAttribute("orders", ordersService.getAllOrders());
    model.addAttribute("categories", categoriesService.getCategories());
    model.addAttribute("searchingParams", new SearchingParams());
    return "orders";
  }

}

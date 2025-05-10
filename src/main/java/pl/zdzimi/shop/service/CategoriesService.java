package pl.zdzimi.shop.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.zdzimi.shop.model.CategoryDTO;
import pl.zdzimi.shop.model.data.Category;
import pl.zdzimi.shop.repository.CategoriesRepository;
import pl.zdzimi.shop.repository.CommoditiesRepository;
import pl.zdzimi.shop.service.exception.CannotDeleteCategory;
import pl.zdzimi.shop.service.exception.CategoryNotFoundException;

@Service
@RequiredArgsConstructor
public class CategoriesService {

  private final CategoriesRepository categoriesRepository;
  private final CommoditiesRepository commoditiesRepository;

  public Collection<CategoryDTO> getCategories() {
    Collection<CategoryDTO> categories = new ArrayList<>();
    CategoryDTO categoryDTO = new CategoryDTO();
    categoryDTO.setId(0L);
    categoryDTO.setName("Wszystkie kategorie");
    categories.add(categoryDTO);
    categories.addAll(getAll());
    return categories;
  }

  public Collection<CategoryDTO> getAll() {
    return categoriesRepository.findAll().stream()
        .map(Mapper::mapToDTO)
        .collect(Collectors.toList());
  }

  public void create(CategoryDTO categoryDTO) {
    categoriesRepository.save(Mapper.map(categoryDTO));
  }

  public void delete(Long id) {
    if (commoditiesRepository.findByCategoryId(id).isEmpty()) {
      categoriesRepository.findById(id).ifPresent(categoriesRepository::delete);
    } else {
      throw new CannotDeleteCategory("Could not delete category: " + id);
    }
  }

  private static class Mapper {

    static CategoryDTO mapToDTO(Category category) {
      CategoryDTO categoryDTO = new CategoryDTO();
      categoryDTO.setId(category.getId());
      categoryDTO.setName(category.getName());
      return categoryDTO;
    }

    static Category map(CategoryDTO categoryDTO) {
      Category category = new Category();
      category.setName(categoryDTO.getName());
      return category;
    }

  }

}

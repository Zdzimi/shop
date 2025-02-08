package pl.zdzimi.shop.service;

import java.util.Collection;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.zdzimi.shop.model.CategoryDTO;
import pl.zdzimi.shop.model.data.Category;
import pl.zdzimi.shop.repository.CategoriesRepository;

@Service
@RequiredArgsConstructor
public class CategoriesService {

  private final CategoriesRepository categoriesRepository;

  public Collection<CategoryDTO> getAll() {
    return categoriesRepository.findAll().stream()
        .map(Mapper::mapToDTO)
        .collect(Collectors.toList());
  }

  public void create(CategoryDTO categoryDTO) {
    categoriesRepository.save(Mapper.map(categoryDTO));
  }

  public void delete(Long id) {
    categoriesRepository.findById(id).ifPresent(categoriesRepository::delete);
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

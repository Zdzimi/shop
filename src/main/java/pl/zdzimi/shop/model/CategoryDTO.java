package pl.zdzimi.shop.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import pl.zdzimi.shop.model.validation.UniqueCategory;

@Getter
@Setter
public class CategoryDTO {

  private Long id;
  @NotBlank(message = "Podaj nazwę kategorii.")
  @UniqueCategory
  private String name;

}

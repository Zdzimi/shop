package pl.zdzimi.shop.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class CreatingCommodity {

  private Long id;
  @NotBlank(message = "Podaj nazwę produktu")
  @Size(min = 4, max = 20, message = "Min 4 znaki max 20 znaków")
  private String name;
  @NotBlank(message = "Podaj opis")
  @Size(min = 4, max = 200, message = "Min 4 znaki max 200 znaków")
  private String description;
  @Min(0)
  private Double price;
  private Long categoryId;

  private MultipartFile photoFirst;
  @NotBlank(message = "Podaj nazę")
  @Size(min = 4, max = 20, message = "Min 4 znaki max 20 znaków")
  private String photoFirstName;
  private String photoFirstDescription;
  private String photoFirstHint;
  private Double photoFirstWidth;
  private Double photoFirstHeight;

  private MultipartFile photoSecond;
  private String photoSecondName;
  private String photoSecondDescription;
  private String photoSecondHint;
  private Double photoSecondWidth;
  private Double photoSecondHeight;

  private Integer amount;

}

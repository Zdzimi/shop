package pl.zdzimi.shop.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import pl.zdzimi.shop.model.validation.ZipCode;

@Getter
@Setter
public class AddressDTO {

  private Long id;
  @NotBlank(message = "Podaj województwo")
  @Size(min = 4, max = 20, message = "Min 4 znaki max 20 znaków")
  private String province;
  @NotBlank(message = "Podaj miasto")
  @Size(min = 4, max = 20, message = "Min 4 znaki max 20 znaków")
  private String city;
  @NotBlank(message = "Podaj ulicę")
  @Size(min = 4, max = 20, message = "Min 4 znaki max 20 znaków")
  private String street;
  @NotBlank(message = "Podaj numer budynku")
  @Size(min = 1, max = 5, message = "Min 1 znak max 5 znaków")
  private String buildingNumber;
  private String apartmentNumber;
  @NotBlank(message = "Podaj kod pocztowy")
  @ZipCode
  private String zipCode;

}

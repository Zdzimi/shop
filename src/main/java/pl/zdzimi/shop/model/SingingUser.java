package pl.zdzimi.shop.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import pl.zdzimi.shop.model.validation.Password;
import pl.zdzimi.shop.model.validation.UniqueEmail;

@Getter
@Setter
public class SingingUser {

  @NotBlank(message = "Imię jest wymagane.")
  @Size(min = 3, max = 25, message = "Imię musi zawierać od 3 do 25 znaków.")
  private String name;

  @NotBlank(message = "Nazwisko jest wymagane.")
  @Size(min = 3, max = 35, message = "Nazwisko musi zawierać od 3 do 35 znaków.")
  private String surname;

  @NotBlank(message = "E-mail jest wymagany.")
  @Email(message = "Niepoprawny adres e-mail.")
  @UniqueEmail
  private String email;

  @NotBlank(message = "Hasło jest wymagane.")
  @Password
  private String password;

}

package pl.zdzimi.shop.model;

import lombok.Getter;
import lombok.Setter;
import pl.zdzimi.shop.model.validation.Password;

@Getter
@Setter
public class Pass {

  @Password
  private String password;

}

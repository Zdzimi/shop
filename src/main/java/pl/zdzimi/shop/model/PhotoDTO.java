package pl.zdzimi.shop.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhotoDTO {

  private Long id;
  private String url;
  private String name;
  private String description;
  private String hint;
  private double width;
  private double height;

}

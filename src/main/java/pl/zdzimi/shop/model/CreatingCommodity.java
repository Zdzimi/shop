package pl.zdzimi.shop.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class CreatingCommodity {

  private Long id;
  private String name;
  private String description;
  private double price;
  private Long categoryId;

  private MultipartFile photoFirst;
  private String photoFirstName;
  private String photoFirstDescription;
  private String photoFirstHint;
  private double photoFirstWidth;
  private double photoFirstHeight;

  private MultipartFile photoSecond;
  private String photoSecondName;
  private String photoSecondDescription;
  private String photoSecondHint;
  private double photoSecondWidth;
  private double photoSecondHeight;

  private int amount;

}

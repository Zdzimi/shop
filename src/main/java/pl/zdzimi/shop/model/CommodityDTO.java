package pl.zdzimi.shop.model;

import java.util.Collection;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommodityDTO {

  private Long id;
  private String name;
  private String description;
  private double price;
  private String categoryName;
  private Collection<PhotoDTO> photos;
  private int amount;

}

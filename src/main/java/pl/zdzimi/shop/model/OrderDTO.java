package pl.zdzimi.shop.model;

import java.time.LocalDateTime;
import java.util.Collection;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDTO {

  private Long id;
  private LocalDateTime dateTime;

  private String name;
  private String surname;
  private String email;

  private String province;
  private String city;
  private String street;
  private String buildingNumber;
  private String apartmentNumber;
  private String zipCode;

  private Collection<ReceiptItem> receiptItems;

}

package pl.zdzimi.shop.model.data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ADDRESSES")
@Getter
@Setter
public class Address {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Long id;
  @Column(name = "PROVINCE")
  private String province;
  @Column(name = "CITY")
  private String city;
  @Column(name = "STREET")
  private String street;
  @Column(name = "BUILDING_NUMBER")
  private String buildingNumber;
  @Column(name = "APARTMENT_NUMBER")
  private String apartmentNumber;
  @Column(name = "ZIP_CODE")
  private String zipCode;
  @Column(name = "IS_ACTUAL")
  private boolean isActual;
  @ManyToOne
  @JoinColumn(name = "USERS_ID")
  private User user;

}

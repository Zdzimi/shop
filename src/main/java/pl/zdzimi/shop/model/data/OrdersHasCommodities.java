package pl.zdzimi.shop.model.data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ORDERS_HAS_COMMODITIES")
@IdClass(OrdersHasCommoditiesId.class)
@Getter
@Setter
public class OrdersHasCommodities {

  @Id
  @ManyToOne
  @JoinColumn(name = "COMMODITIES_ID")
  private Commodity commodity;
  @Id
  @ManyToOne
  @JoinColumn(name = "ORDERS_ID")
  private Order order;
  @Column(name = "AMOUNT")
  private int amount;

}

package pl.zdzimi.shop.model.data;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ORDERS")
@Getter
@Setter
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Long id;
  @Column(name = "DATE_TIME")
  private LocalDateTime dateTime;
  @ManyToOne
  @JoinColumn(name = "USERS_ID")
  private User user;
  @ManyToOne
  @JoinColumn(name = "ADDRESSES_ID")
  private Address address;
  @OneToMany
  @JoinColumn(name = "ORDERS_ID")
  private Collection<OrdersHasCommodities> commodities;

}

package pl.zdzimi.shop.model.data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "AMOUNTS")
@Getter
@Setter
public class Amount {

  @Id
  @Column(name = "ID")
  private Long id;
  @Column(name = "AMOUNT")
  private int amount;
  @OneToOne
  @JoinColumn(name = "ID")
  private Commodity commodity;

}

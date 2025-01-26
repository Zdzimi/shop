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
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Long id;
  private int amount;
  @OneToOne
  @JoinColumn(name = "COMMODITIES_ID")
  private Commodity commodity;

}

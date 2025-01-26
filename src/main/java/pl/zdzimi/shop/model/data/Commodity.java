package pl.zdzimi.shop.model.data;

import jakarta.persistence.*;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "COMMODITIES")
@Getter
@Setter
public class Commodity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Long id;
  @Column(name = "NAME")
  private String name;
  @Column(name = "DESCRIPTION")
  private String description;
  @Column(name = "PRICE")
  private double price;
  @ManyToOne
  @JoinColumn(name = "CATEGORIES_ID")
  private Category category;
  @OneToMany
  @JoinColumn(name = "COMMODITIES_ID")
  private List<Photo> photos;
  @OneToOne
  @JoinColumn(name = "COMMODITIES_ID")
  private Amount amount;

}

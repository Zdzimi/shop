package pl.zdzimi.shop.model.data;

import jakarta.persistence.*;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "CATEGORIES")
@Getter
@Setter
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Long id;
  @Column(name = "NAME")
  private String name;
//  @OneToMany
//  @JoinColumn(name = "CATEGORIES_ID")
//  private List<Commodity> commodities;

}

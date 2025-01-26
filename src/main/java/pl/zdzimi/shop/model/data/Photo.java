package pl.zdzimi.shop.model.data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PHOTOS")
@Getter
@Setter
public class Photo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Long id;
  @Column(name = "URL")
  private String url;
  @Column(name = "NAME")
  private String name;
  @Column(name = "DESCRIPTION")
  private String description;
  @Column(name = "HINT")
  private String hint;
  @Column(name = "WIDTH")
  private double width;
  @Column(name = "HEIGHT")
  private double height;
  @ManyToOne
  @JoinColumn(name = "COMMODITIES_ID")
  private Commodity commodity;

}

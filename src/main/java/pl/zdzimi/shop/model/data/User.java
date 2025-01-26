package pl.zdzimi.shop.model.data;

import jakarta.persistence.*;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "USERS")
@Getter
@Setter
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Long id;
  @Column(name = "NAME")
  private String name;
  @Column(name = "SURNAME")
  private String surname;
  @Column(name = "EMAIL")
  private String email;
  @Column(name = "PASSWORD")
  private String password;
  @Column(name = "ROLE")
  private String role;
  @Column(name = "CODE")
  private String code;
  @OneToMany
  @JoinColumn(name = "USERS_ID")
  private List<Address> addresses;
  @OneToMany
  @JoinColumn(name = "USERS_ID")
  private List<Order> orders;

}

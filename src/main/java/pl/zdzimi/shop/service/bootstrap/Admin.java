package pl.zdzimi.shop.service.bootstrap;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
@ConfigurationProperties(prefix = "admin")
@Getter
@Setter
public class Admin {

  private String name;
  private String surname;
  private String email;
  private String password;

}

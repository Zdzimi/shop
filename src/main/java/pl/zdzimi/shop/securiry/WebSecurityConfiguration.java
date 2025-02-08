package pl.zdzimi.shop.securiry;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class WebSecurityConfiguration {

  private final UserDetailsService userDetailsService;
  private final PasswordEncoder passwordEncoder;

  @Bean
  public DaoAuthenticationProvider daoAuthenticationProvider() {
    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    authenticationProvider.setUserDetailsService(userDetailsService);
    authenticationProvider.setPasswordEncoder(passwordEncoder);
    return authenticationProvider;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(
        req -> req
            .requestMatchers(
                "/shop",
                "/shop/login",
                "/shop/registration",
                "/shop/password-reset",
                "/shop/password-reset/*",
                "/shop/product/*",
                "/shop/shopping-cart",
                "/shop/category/*"
            ).permitAll()
            .requestMatchers(
                "shop/admin",
                "shop/admin/*"
            ).hasRole("ADMIN")
            .anyRequest().authenticated()
    ).formLogin(
        form -> form
            .loginPage("/shop/login")
            .defaultSuccessUrl("/shop/account")
    ).logout(
        logout -> logout
            .logoutUrl("/shop/logout").permitAll()
            .logoutSuccessUrl("/shop")
    );
    return http.build();
  }

}

package pl.zdzimi.shop.service.bootstrap;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.zdzimi.shop.model.Role;
import pl.zdzimi.shop.model.data.User;
import pl.zdzimi.shop.repository.UsersRepository;

@Service
@RequiredArgsConstructor
public class BootstrapDatabase {

  private final UsersRepository usersRepository;
  private final Admin admin;
  private final PasswordEncoder passwordEncoder;

  @EventListener(ApplicationReadyEvent.class)
  public void createAdmin() {
    if (usersRepository.findByEmail(admin.getEmail()).isEmpty()) {
      User user = new User();
      user.setName(admin.getName());
      user.setSurname(admin.getSurname());
      user.setEmail(admin.getEmail());
      user.setPassword(passwordEncoder.encode(admin.getPassword()));
      user.setRole(Role.ROLE_ADMIN.name());
      usersRepository.save(user);
    }
  }

}

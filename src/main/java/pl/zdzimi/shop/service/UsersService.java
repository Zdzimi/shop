package pl.zdzimi.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;
import pl.zdzimi.shop.model.Role;
import pl.zdzimi.shop.model.SingingUser;
import pl.zdzimi.shop.model.data.User;
import pl.zdzimi.shop.repository.UsersRepository;

@Service
@RequiredArgsConstructor
public class UsersService {

  private final UsersRepository usersRepository;
  private final PasswordEncoder passwordEncoder;

  public void createCustomer(SingingUser singingUser) {
    String password = passwordEncoder.encode(singingUser.getPassword());
    User customer = Mapper.mapToCustomer(singingUser, password);
    usersRepository.save(customer);
  }

  private static class Mapper {

    static User mapToCustomer(SingingUser singingUser, String password) {
      User user = new User();
      user.setName(HtmlUtils.htmlEscape(singingUser.getName()));
      user.setSurname(HtmlUtils.htmlEscape(singingUser.getSurname()));
      user.setEmail(singingUser.getEmail());
      user.setPassword(password);
      user.setRole(Role.ROLE_USER.name());
      return user;
    }

  }

}

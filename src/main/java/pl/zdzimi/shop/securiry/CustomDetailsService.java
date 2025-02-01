package pl.zdzimi.shop.securiry;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.zdzimi.shop.model.data.User;
import pl.zdzimi.shop.repository.UsersRepository;

@RequiredArgsConstructor
@Service
public class CustomDetailsService implements UserDetailsService {

  private final UsersRepository usersRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    User user = usersRepository.findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException("Could not find user: " + email));
    return new PrincipalUser(user);
  }

}

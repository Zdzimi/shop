package pl.zdzimi.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.zdzimi.shop.model.data.User;
import pl.zdzimi.shop.repository.UsersRepository;
import pl.zdzimi.shop.service.exception.CodeNotFoundException;
import pl.zdzimi.shop.service.exception.UserNotFoundException;

@RequiredArgsConstructor
@Service
public class PasswordRecoveryService {

  private final UsersRepository usersRepository;
  private final CodesService codesService;
  private final MailsService mailsService;
  private final PasswordEncoder passwordEncoder;

  public String proceedForgotPasswordRequest(String username) {
    User user = usersRepository.findByEmail(username)
        .orElseThrow(() -> new UserNotFoundException("Nie znaleziono użytkownika: " + username));
    String code = codesService.prepareCode(user.getId());
    user.setCode(code);
    user = usersRepository.save(user);
    mailsService.sendCode(user.getEmail(), user.getCode());
    return user.getEmail();
  }

  public boolean isNotExpired(String code) {
    usersRepository.findByCode(code)
        .orElseThrow(() -> new CodeNotFoundException("Nie znaleziono kodu: " + code));
    return codesService.isNotExpired(code);
  }

  public void changePassword(String code, String password) {
    User user = usersRepository.findByCode(code)
        .orElseThrow(() -> new CodeNotFoundException("Nie znaleziono kodu: " + code));
    user.setPassword(passwordEncoder.encode(password));
    user.setCode(null);
    usersRepository.save(user);
  }

}

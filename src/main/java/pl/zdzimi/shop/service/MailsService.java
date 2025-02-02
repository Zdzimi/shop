package pl.zdzimi.shop.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import pl.zdzimi.shop.controller.LoginController;

@RequiredArgsConstructor
@Service
public class MailsService {

  private final JavaMailSender mailSender;

  public void sendCode(String email, String code) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setSubject("Odzyskiwanie hasła.");
    message.setTo(email);
    message.setText(
        String.format(
            "Link do zmiany hasła:\n%s\nAktywny przez 10 min.",
            linkTo(LoginController.class)
                .slash("password-reset")
                .slash(code)
                .withRel("Zmaina hasła.")
        )
    );
    mailSender.send(message);
  }

}

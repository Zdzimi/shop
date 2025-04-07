package pl.zdzimi.shop.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import pl.zdzimi.shop.controller.LoginController;
import pl.zdzimi.shop.model.Bill;
import pl.zdzimi.shop.model.ReceiptItem;

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

  public void sendBill(String email, Bill bill) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setSubject("Nowe zamówienie.");
    message.setTo(email);
    StringBuilder sb = new StringBuilder();

    sb.append("Dziękujemy za zamówienie: \n");

    for (ReceiptItem item : bill.getItems()) {
      sb.append(item.getCommodity().getName())
          .append(" ")
          .append(item.getCommodity().getPrice())
          .append(" * ")
          .append(item.getAmount())
          .append(" = ")
          .append(item.getPrice())
          .append("\n");
    }
    sb.append("Suma = ").append(bill.getSum());

    message.setText(sb.toString());
    mailSender.send(message);
  }

}

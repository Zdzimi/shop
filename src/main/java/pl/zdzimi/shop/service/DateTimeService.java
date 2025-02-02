package pl.zdzimi.shop.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

@Service
public class DateTimeService {

  public LocalDateTime getCurrentDateTime() {
    return LocalDateTime.now();
  }

  public LocalDate getCurrentDate() {
    return LocalDate.now();
  }

}

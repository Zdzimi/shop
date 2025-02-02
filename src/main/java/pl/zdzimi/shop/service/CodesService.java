package pl.zdzimi.shop.service;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CodesService {

  private final DateTimeService dateTimeService;

  public String prepareCode(Long id) {
    LocalDateTime expirationDateTime = dateTimeService.getCurrentDateTime().plusMinutes(10);
    StringBuilder sb = new StringBuilder();
    sb
        .append(Long.toHexString(id)).append('-')
        .append(Integer.toHexString(expirationDateTime.getYear())).append('-')
        .append(Integer.toHexString(expirationDateTime.getMonthValue())).append('-')
        .append(Integer.toHexString(expirationDateTime.getDayOfMonth())).append('-')
        .append(Integer.toHexString(expirationDateTime.getHour())).append('-')
        .append(Integer.toHexString(expirationDateTime.getMinute())).append('-')
        .append(Integer.toHexString(expirationDateTime.getSecond()));
    return sb.toString();
  }

  public boolean isNotExpired(String code) {
    String[] split = code.split("-");
    LocalDateTime now = dateTimeService.getCurrentDateTime();
    LocalDateTime expirationTime = LocalDateTime.of(
        Integer.parseInt(split[1], 16), // year
        Integer.parseInt(split[2], 16), // month
        Integer.parseInt(split[3], 16), // day
        Integer.parseInt(split[4], 16), // hour
        Integer.parseInt(split[5], 16), // minute
        Integer.parseInt(split[6], 16)  // second
    );
    return now.isBefore(expirationTime);
  }

}

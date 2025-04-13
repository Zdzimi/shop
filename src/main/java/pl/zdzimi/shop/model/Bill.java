package pl.zdzimi.shop.model;

import java.math.BigDecimal;
import java.util.Collection;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bill {

  private Collection<ReceiptItem> items;
  private BigDecimal sum;

  public Bill(Collection<ReceiptItem> items) {
    this.items = items;
    this.sum = new BigDecimal(0);
    for (ReceiptItem item : items) {
      this.sum = this.getSum().add(item.getPrice());
    }
  }

}

package pl.zdzimi.shop.model;

import java.util.Collection;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bill {

  private Collection<ReceiptItem> items;
  private double sum;

  public Bill(Collection<ReceiptItem> items) {
    this.items = items;
    for (ReceiptItem item : items) {
      this.sum += item.getPrice();
    }
  }

}

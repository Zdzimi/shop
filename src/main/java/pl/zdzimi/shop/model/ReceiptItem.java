package pl.zdzimi.shop.model;

import java.util.Objects;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReceiptItem {

  private CommodityDTO commodity;
  private int amount;
  private double price;

  public ReceiptItem(CommodityDTO commodity, int amount) {
    this.commodity = commodity;
    this.amount = amount;
    this.price = commodity.getPrice() * amount;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ReceiptItem)) {
      return false;
    }
    ReceiptItem that = (ReceiptItem) o;
    return this.getCommodity().getId().equals(that.getCommodity().getId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getCommodity());
  }

}

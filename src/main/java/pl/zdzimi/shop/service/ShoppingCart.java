package pl.zdzimi.shop.service;

import java.util.Map;
import java.util.TreeMap;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import pl.zdzimi.shop.model.CommodityDTO;
import pl.zdzimi.shop.model.ReceiptItem;

@Component
@Scope(
    value = WebApplicationContext.SCOPE_SESSION,
    proxyMode = ScopedProxyMode.TARGET_CLASS
)
@Getter
@Setter
public class ShoppingCart {

  private Map<Long, ReceiptItem> items = new TreeMap<>();

  public void add(CommodityDTO commodityDTO, int amount) {
    if (items.containsKey(commodityDTO.getId())) {
      ReceiptItem receiptItem = items.get(commodityDTO.getId());
      this.items.put(commodityDTO.getId(), new ReceiptItem(commodityDTO, receiptItem.getAmount() + amount));
    } else {
      items.put(commodityDTO.getId(), new ReceiptItem(commodityDTO, amount));
    }
  }

  public void remove(Long id) {
    this.items.remove(id);
  }

  public void update(Long id, int amount) {
    this.items.put(
        id,
        new ReceiptItem(this.items.get(id).getCommodity(), amount)
    );
  }

}

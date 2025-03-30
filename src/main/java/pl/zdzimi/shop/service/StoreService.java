package pl.zdzimi.shop.service;

import java.util.Collection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.zdzimi.shop.model.CommodityDTO;
import pl.zdzimi.shop.model.ReceiptItem;

@Service
@RequiredArgsConstructor
public class StoreService {

  private final ShoppingCart shoppingCart;
  private final CommoditiesService commoditiesService;

  public void add(Long id, int amount) {
    if (amount > 0) {
      CommodityDTO commodityDTO = commoditiesService.findById(id);
      shoppingCart.add(commodityDTO, amount);
    }
  }

  public Collection<ReceiptItem> getShoppingCart() {
    return shoppingCart.getItems().values();
  }

  public void remove(Long id) {
    shoppingCart.remove(id);
  }

  public void update(Long id, int amount) {
    if (amount > 0) {
      shoppingCart.update(id, amount);
    }
  }

}

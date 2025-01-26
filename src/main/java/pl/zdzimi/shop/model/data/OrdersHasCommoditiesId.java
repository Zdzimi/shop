package pl.zdzimi.shop.model.data;

import java.io.Serializable;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class OrdersHasCommoditiesId implements Serializable {

  private Commodity commodity;
  private Order order;

}

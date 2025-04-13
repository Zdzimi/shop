package pl.zdzimi.shop.service;

import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.zdzimi.shop.model.Bill;
import pl.zdzimi.shop.model.OrderDTO;
import pl.zdzimi.shop.model.ReceiptItem;
import pl.zdzimi.shop.model.data.Amount;
import pl.zdzimi.shop.model.data.Commodity;
import pl.zdzimi.shop.model.data.Order;
import pl.zdzimi.shop.model.data.OrdersHasCommodities;
import pl.zdzimi.shop.model.data.User;
import pl.zdzimi.shop.repository.AmountsRepository;
import pl.zdzimi.shop.repository.OrdersHasCommoditiesRepository;
import pl.zdzimi.shop.repository.OrdersRepository;
import pl.zdzimi.shop.service.exception.CommodityNotFoundException;
import pl.zdzimi.shop.service.exception.InsufficientQuantityException;

@Service
@RequiredArgsConstructor
public class OrdersService {

  private final OrdersRepository ordersRepository;
  private final OrdersHasCommoditiesRepository ordersHasCommoditiesRepository;
  private final StoreService storeService;
  private final DateTimeService dateTimeService;
  private final AddressesService addressesService;
  private final CommoditiesService commoditiesService;
  private final AmountsRepository amountsRepository;
  private final MailsService mailsService;

  @Transactional
  public void order(User user, Long addressId) {
    Collection<ReceiptItem> shoppingCart = storeService.getShoppingCart();
    Order order = createAnSaveOrder(user, addressId);
    for (ReceiptItem item : shoppingCart) {
      createAndSaveReceiptItem(item, order);
    }
    mailsService.sendBill(user.getEmail(), new Bill(shoppingCart));
    storeService.clearShoppingCart();
  }

  private void createAndSaveReceiptItem(ReceiptItem item, Order order) {
    Commodity commodity = commoditiesService.find(item.getCommodity().getId());
    Amount amount = amountsRepository.findByCommodity(commodity)
        .orElseThrow(() -> new CommodityNotFoundException(commodity.getId()));
    if (amount.getAmount() < item.getAmount()) {
      throw new InsufficientQuantityException(commodity.getName() + " - zostało tylko " + amount.getAmount() + " szt.");
    }
    OrdersHasCommodities ohc = new OrdersHasCommodities();
    ohc.setOrder(order);
    ohc.setAmount(item.getAmount());
    ohc.setCommodity(commodity);
    ordersHasCommoditiesRepository.save(ohc);
    amount.setAmount(
        amount.getAmount() - item.getAmount()
    );
    amountsRepository.save(amount);
  }

  private Order createAnSaveOrder(User user, Long addressId) {
    Order order = new Order();
    order.setDateTime(dateTimeService.getCurrentDateTime());
    order.setUser(user);
    order.setAddress(addressesService.findAddress(user, addressId));
    return ordersRepository.save(order);
  }

  public Collection<OrderDTO> getAllOrders() {
    return ordersRepository.findAllOrders().stream()
        .map(o -> Mapper.map(o, commoditiesService))
        .collect(Collectors.toList());
  }

  public Collection<OrderDTO> getAllMyOrders(User user) {
    return ordersRepository.findAllMyOrders(user).stream()
        .map(o -> Mapper.map(o, commoditiesService))
        .collect(Collectors.toList());
  }

  private static class Mapper {

    private static OrderDTO map(Order order, CommoditiesService commoditiesService) {
      OrderDTO orderDTO = new OrderDTO();

      orderDTO.setId(order.getId());
      orderDTO.setDateTime(order.getDateTime());

      orderDTO.setName(order.getUser().getName());
      orderDTO.setSurname(order.getUser().getSurname());
      orderDTO.setEmail(order.getUser().getEmail());

      orderDTO.setProvince(order.getAddress().getProvince());
      orderDTO.setCity(order.getAddress().getCity());
      orderDTO.setStreet(order.getAddress().getStreet());
      orderDTO.setBuildingNumber(order.getAddress().getBuildingNumber());
      orderDTO.setApartmentNumber(order.getAddress().getApartmentNumber());
      orderDTO.setZipCode(order.getAddress().getZipCode());

      Bill bill = getBill(order, commoditiesService);

      orderDTO.setReceiptItems(bill.getItems());
      orderDTO.setSum(bill.getSum());

      return orderDTO;
    }

    private static Bill getBill(Order order, CommoditiesService commoditiesService) {
      List<ReceiptItem> result = new ArrayList<>();
      for (OrdersHasCommodities ohc : order.getCommodities()) {
        result.add(new ReceiptItem(commoditiesService.mapToCommodityDTO(ohc.getCommodity()), ohc.getAmount()));
      }
      return new Bill(result);
    }

  }

}

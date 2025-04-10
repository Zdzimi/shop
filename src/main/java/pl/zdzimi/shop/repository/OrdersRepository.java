package pl.zdzimi.shop.repository;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.zdzimi.shop.model.data.Order;
import pl.zdzimi.shop.model.data.User;

@Repository
public interface OrdersRepository extends JpaRepository<Order, Long> {

  @Query("""
SELECT o FROM Order o
LEFT JOIN FETCH o.user
LEFT JOIN FETCH o.address
LEFT JOIN FETCH o.commodities c
LEFT JOIN FETCH c.commodity
ORDER BY o.dateTime DESC
""")
  Collection<Order> findAllOrders();

  @Query("""
SELECT o FROM Order o
LEFT JOIN FETCH o.user
LEFT JOIN FETCH o.address
LEFT JOIN FETCH o.commodities c
LEFT JOIN FETCH c.commodity
WHERE o.user = ?1
ORDER BY o.dateTime DESC
""")
  Collection<Order> findAllMyOrders(User user);

}

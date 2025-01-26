package pl.zdzimi.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.zdzimi.shop.model.data.OrdersHasCommodities;
import pl.zdzimi.shop.model.data.OrdersHasCommoditiesId;

@Repository
interface OrdersHasCommoditiesRepository extends JpaRepository<OrdersHasCommodities, OrdersHasCommoditiesId> {

}

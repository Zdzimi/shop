package pl.zdzimi.shop.repository;

import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.zdzimi.shop.model.data.Commodity;

@Repository
public interface CommoditiesRepository extends JpaRepository<Commodity, Long> {

  @Query("""
  SELECT c FROM Commodity c 
  LEFT JOIN FETCH c.amount
  LEFT JOIN FETCH c.category 
  LEFT JOIN FETCH c.photos
  ORDER BY c.amount.amount
  """)
  Collection<Commodity> findAllCommodities();

}

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

  @Query("""
  SELECT c FROM Commodity c 
  LEFT JOIN FETCH c.amount
  LEFT JOIN FETCH c.category 
  LEFT JOIN FETCH c.photos
  WHERE c.amount.amount > 0
  ORDER BY c.amount.amount DESC 
  """)
  Collection<Commodity> findAllAvailable();

  @Query("""
  SELECT c FROM Commodity c 
  LEFT JOIN FETCH c.amount
  LEFT JOIN FETCH c.category 
  LEFT JOIN FETCH c.photos
  WHERE c.name like %?1% AND c.category.id = ?2 AND c.amount.amount > 0
  ORDER BY c.amount.amount DESC 
  """)
  Collection<Commodity> findAllAvailableBySearchingParams(String name, Long categoryId);

  @Query("""
  SELECT c FROM Commodity c 
  LEFT JOIN FETCH c.amount
  LEFT JOIN FETCH c.category 
  LEFT JOIN FETCH c.photos
  WHERE c.name like %?1% AND c.amount.amount > 0
  ORDER BY c.amount.amount DESC 
  """)
  Collection<Commodity> findAllAvailableByNameLike(String name);

  @Query("SELECT c FROM Commodity c LEFT JOIN FETCH c.category cat where cat.id = ?1")
  Collection<Commodity> findByCategoryId(Long id);

}

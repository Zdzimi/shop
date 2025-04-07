package pl.zdzimi.shop.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.zdzimi.shop.model.data.Amount;
import pl.zdzimi.shop.model.data.Commodity;

@Repository
public interface AmountsRepository extends JpaRepository<Amount, Long> {

  Optional<Amount> findByCommodity(Commodity commodity);

}

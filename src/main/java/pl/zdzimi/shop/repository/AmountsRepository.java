package pl.zdzimi.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.zdzimi.shop.model.data.Amount;

@Repository
public interface AmountsRepository extends JpaRepository<Amount, Long> {

}
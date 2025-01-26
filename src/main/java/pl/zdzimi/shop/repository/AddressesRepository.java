package pl.zdzimi.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.zdzimi.shop.model.data.Address;

@Repository
public interface AddressesRepository extends JpaRepository<Address, Long> {

}

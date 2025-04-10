package pl.zdzimi.shop.repository;

import java.util.Collection;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.zdzimi.shop.model.data.Address;
import pl.zdzimi.shop.model.data.User;

@Repository
public interface AddressesRepository extends JpaRepository<Address, Long> {

  @Query(" SELECT a FROM Address a WHERE a.isActual = true AND a.user = ?1")
  Collection<Address> findByUser(User user);

  Optional<Address> findByIdAndUser(Long id, User user);

  Optional<Address> findByUserAndId(User user, Long addressId);

}

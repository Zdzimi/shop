package pl.zdzimi.shop.repository;

import java.util.Collection;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.zdzimi.shop.model.data.User;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {

  Optional<User> findByEmail(String email);

  Optional<User> findByCode(String code);

  @Query("select u from User u where u.role = ?1")
  Collection<User> findAllEmployees(String role);

}

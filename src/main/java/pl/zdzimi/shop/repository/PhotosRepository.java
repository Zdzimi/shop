package pl.zdzimi.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.zdzimi.shop.model.data.Photo;

@Repository
public interface PhotosRepository extends JpaRepository<Photo, Long> {

}

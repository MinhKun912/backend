package ra.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.model.entity.ImageData;

import java.util.Optional;

public interface StorageRepository extends JpaRepository<ImageData,Long> {
    Optional<ImageData> findByName(String fileName);
}

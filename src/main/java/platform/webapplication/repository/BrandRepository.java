package platform.webapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import platform.webapplication.entities.Brands;

public interface BrandRepository extends JpaRepository<Brands, Integer> {
}

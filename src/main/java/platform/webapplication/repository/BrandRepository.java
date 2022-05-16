package platform.webapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import platform.webapplication.entities.Brand;
import platform.webapplication.entities.Cart;

public interface BrandRepository extends JpaRepository<Brand, Integer> {
}

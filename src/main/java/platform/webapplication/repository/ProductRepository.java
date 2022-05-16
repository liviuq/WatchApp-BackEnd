package platform.webapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import platform.webapplication.entities.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT p FROM Product p WHERE trim(lower(p.brand)) LIKE trim(lower(?1))"
            + " OR CONCAT(p.price, '') LIKE %?1%"
            + " OR p.carcase LIKE %?1%"
            + " OR p.glass LIKE %?1%")
    List<Product> search(String filter);
}

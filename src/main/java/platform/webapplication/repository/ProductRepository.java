package platform.webapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import platform.webapplication.enitites.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}

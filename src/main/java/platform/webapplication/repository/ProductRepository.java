package platform.webapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import platform.webapplication.model.Product;
import platform.webapplication.model.User;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}

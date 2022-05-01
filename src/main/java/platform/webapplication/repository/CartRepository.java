package platform.webapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import platform.webapplication.enitites.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
}

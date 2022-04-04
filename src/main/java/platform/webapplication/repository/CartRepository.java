package platform.webapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import platform.webapplication.model.Cart;
import platform.webapplication.model.User;

public interface CartRepository extends JpaRepository<Cart, Integer> {
}

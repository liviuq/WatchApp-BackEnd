package platform.webapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import platform.webapplication.model.Order;
import platform.webapplication.model.User;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}

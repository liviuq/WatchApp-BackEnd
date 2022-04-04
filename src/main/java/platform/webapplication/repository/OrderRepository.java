package platform.webapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import platform.webapplication.model.Order;
import platform.webapplication.model.User;

public interface OrderRepository extends CrudRepository<Order, Integer> {
}

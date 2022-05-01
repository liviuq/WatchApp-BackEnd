package platform.webapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import platform.webapplication.enitites.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}

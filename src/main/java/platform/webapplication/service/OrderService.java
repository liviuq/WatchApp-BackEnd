package platform.webapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.webapplication.model.Order;
import platform.webapplication.model.User;
import platform.webapplication.repository.OrderRepository;
import platform.webapplication.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> findAll() {

        var it = orderRepository.findAll();

        var orders = new ArrayList<Order>();
        it.forEach(e -> orders.add(e));

        return orders;
    }

    public Order findById(Integer id)
    {
        var order = orderRepository.findById(id);
        return order.orElse(null);
    }

    public Long count() {

        return orderRepository.count();
    }

    public void deleteById(Integer orderId) {
        orderRepository.deleteById(orderId);
    }
}

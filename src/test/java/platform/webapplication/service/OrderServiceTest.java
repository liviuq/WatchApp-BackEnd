package platform.webapplication.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import platform.webapplication.entities.Order;
import platform.webapplication.repository.CarcaseColorRepository;
import platform.webapplication.repository.OrderRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    private OrderRepository orderRepository;
    private OrderService orderService;
    private List<Order> orders;

    @BeforeEach
    void setUp() {
        orders = new ArrayList<>();
        this.orderRepository = mock(OrderRepository.class);
        when(orderRepository.findAll()).thenReturn(orders);
        doNothing().when(orderRepository).deleteById(anyInt());

        this.orderService = new OrderService(orderRepository);
    }

    @Test
    void findAll() {
        //Arrange
        orders.add(new Order(1, new Date()));
        orders.add(new Order(2, new Date()));
        orders.add(new Order(3, new Date()));
        orders.add(new Order(4, new Date()));

        //Act
        var actual = orderService.findAll();

        //Assert
        assertEquals(orders, actual);
    }

    @Test
    void deleteById() {
        //Act
        orderService.deleteById(anyInt());
        //Assert
        verify(orderRepository, times(1)).deleteById(anyInt());
    }
}
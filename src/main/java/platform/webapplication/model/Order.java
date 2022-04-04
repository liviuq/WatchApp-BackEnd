package platform.webapplication.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer cart_id;
    private Date order_date;

    public Order() {
    }

    public Order(Integer id, Integer cartId, Date orderDate) {
        this.id = id;
        this.cart_id = cartId;
        this.order_date = orderDate;
    }

    public Integer getId() {
        return this.id;
    }

    public Integer getCart_id() {
        return this.cart_id;
    }

    public Date getOrder_date() {
        return this.order_date;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCart_id(Integer cartId) {
        this.cart_id = cartId;
    }

    public void setOrder_date(Date orderDate) {
        this.order_date = orderDate;
    }
}

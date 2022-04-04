package platform.webapplication.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer product_id;
    private Integer buyer_id;
    private Integer seller_id;

    public Cart() {
    }

    public Cart(Integer id, Integer productId, Integer buyerId, Integer sellerId) {
        this.id = id;
        this.product_id = productId;
        this.buyer_id = buyerId;
        this.seller_id = sellerId;
    }

    public Integer getId() {
        return this.id;
    }

    public Integer getProduct_id() {
        return this.product_id;
    }

    public Integer getBuyer_id() {
        return this.buyer_id;
    }

    public Integer getSeller_id() {
        return this.seller_id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setProduct_id(Integer productId) {
        this.product_id = productId;
    }

    public void setBuyer_id(Integer buyerId) {
        this.buyer_id = buyerId;
    }

    public void setSeller_id(Integer sellerId) {
        this.seller_id = sellerId;
    }
}

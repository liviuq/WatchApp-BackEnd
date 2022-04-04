package platform.webapplication.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Favorites {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer product_id;
    private Integer user_id;

    public Favorites() {
    }

    public Favorites(Integer id, Integer productId, Integer userId) {
        this.id = id;
        this.product_id = productId;
        this.user_id = userId;
    }

    public Integer getId() {
        return this.id;
    }

    public Integer getProduct_id() {
        return this.product_id;
    }

    public Integer getUser_id() {
        return this.user_id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setProduct_id(Integer productId) {
        this.product_id = productId;
    }

    public void setUser_id(Integer userId) {
        this.user_id = userId;
    }
}

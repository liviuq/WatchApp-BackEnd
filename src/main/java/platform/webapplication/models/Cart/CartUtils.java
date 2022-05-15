package platform.webapplication.models.Cart;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class CartUtils {
    private Integer id;
    private Integer product_id;
    private String name;
    private Float price;
    private String seller;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartUtils cartUtils = (CartUtils) o;
        return Objects.equals(id, cartUtils.id) && Objects.equals(name, cartUtils.name) && Objects.equals(price, cartUtils.price) && Objects.equals(seller, cartUtils.seller);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, seller);
    }
}

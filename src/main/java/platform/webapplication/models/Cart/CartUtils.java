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
    private String name;
    private Float price;
    private String buyer;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartUtils cartUtils = (CartUtils) o;
        return Objects.equals(id, cartUtils.id) && Objects.equals(name, cartUtils.name) && Objects.equals(price, cartUtils.price) && Objects.equals(buyer, cartUtils.buyer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, buyer);
    }
}

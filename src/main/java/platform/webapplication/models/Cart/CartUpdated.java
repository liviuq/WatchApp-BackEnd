package platform.webapplication.models.Cart;

import lombok.*;
import platform.webapplication.entities.Cart;

import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class CartUpdated {
    private Cart cart = new Cart();
    private String error = "";
    private int statusCode = 500;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        platform.webapplication.models.Cart.CartUpdated that = (platform.webapplication.models.Cart.CartUpdated) o;
        return statusCode == that.statusCode && Objects.equals(cart, that.cart) && Objects.equals(error, that.error);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cart, error, statusCode);
    }
}
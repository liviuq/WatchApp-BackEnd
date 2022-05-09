package platform.webapplication.models.Cart;

import lombok.*;
import platform.webapplication.entities.Cart;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class AllCart {
    private List<Cart> cart = new ArrayList<>();
    private String error = "";
    private int statusCode = 500;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AllCart allCart = (AllCart) o;
        return statusCode == allCart.statusCode && Objects.equals(cart, allCart.cart) && Objects.equals(error, allCart.error);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cart, error, statusCode);
    }
}
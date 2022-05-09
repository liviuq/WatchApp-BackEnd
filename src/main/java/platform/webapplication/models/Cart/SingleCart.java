package platform.webapplication.models.Cart;

import lombok.*;
import platform.webapplication.entities.Cart;

import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class SingleCart {
    private Cart cart = new Cart();
    private String error = "";
    private int StatusCode = 500;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SingleCart that = (SingleCart) o;
        return StatusCode == that.StatusCode && Objects.equals(cart, that.cart) && Objects.equals(error, that.error);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cart, error, StatusCode);
    }
}
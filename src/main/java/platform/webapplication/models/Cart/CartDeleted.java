package platform.webapplication.models.Cart;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class CartDeleted {
    private Integer cartId;
    private String error = "";
    private int statusCode = 500;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartDeleted that = (CartDeleted) o;
        return statusCode == that.statusCode && Objects.equals(cartId, that.cartId) && Objects.equals(error, that.error);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartId, error, statusCode);
    }
}
package platform.webapplication.models.Cart;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class CartTotalPrice {
    private CartExtracted cartExtracted = new CartExtracted();
    private Float totalPrice;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartTotalPrice that = (CartTotalPrice) o;
        return Objects.equals(cartExtracted, that.cartExtracted) && Objects.equals(totalPrice, that.totalPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartExtracted, totalPrice);
    }
}

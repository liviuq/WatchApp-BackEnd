package platform.webapplication.models.Cart;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class CartExtractedProduct {
    private CartUtils cartUtils = new CartUtils();
    private String error = "";
    private int StatusCode = 500;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartExtractedProduct that = (CartExtractedProduct) o;
        return StatusCode == that.StatusCode && Objects.equals(cartUtils, that.cartUtils) && Objects.equals(error, that.error);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartUtils, error, StatusCode);
    }
}

package platform.webapplication.models.Cart;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class CartExtracted {
    private List<CartUtils> cartUtils = new ArrayList<>();
    private String error = "";
    private int StatusCode = 500;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartExtracted that = (CartExtracted) o;
        return StatusCode == that.StatusCode && Objects.equals(cartUtils, that.cartUtils) && Objects.equals(error, that.error);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartUtils, error, StatusCode);
    }
}

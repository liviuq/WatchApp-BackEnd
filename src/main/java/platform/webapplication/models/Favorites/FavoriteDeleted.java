package platform.webapplication.models.Favorites;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class FavoriteDeleted {
    private Integer cartId;
    private String error = "";
    private int statusCode = 500;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavoriteDeleted that = (FavoriteDeleted) o;
        return statusCode == that.statusCode && Objects.equals(cartId, that.cartId) && Objects.equals(error, that.error);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartId, error, statusCode);
    }
}

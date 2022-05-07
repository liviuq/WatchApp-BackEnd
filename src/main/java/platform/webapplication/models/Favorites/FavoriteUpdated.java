package platform.webapplication.models.Favorites;

import lombok.*;
import platform.webapplication.entities.Favorites;

import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class FavoriteUpdated {
    private Favorites favorite = new Favorites();
    private String error = "";
    private int statusCode = 500;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavoriteUpdated that = (FavoriteUpdated) o;
        return statusCode == that.statusCode && Objects.equals(favorite, that.favorite) && Objects.equals(error, that.error);
    }

    @Override
    public int hashCode() {
        return Objects.hash(favorite, error, statusCode);
    }
}

package platform.webapplication.models.Favorites;

import lombok.*;
import platform.webapplication.entities.Favorites;

import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class SingleFavorite {
    private Favorites favorite = new Favorites();
    private String error = "";
    private int StatusCode = 500;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SingleFavorite that = (SingleFavorite) o;
        return StatusCode == that.StatusCode && Objects.equals(favorite, that.favorite) && Objects.equals(error, that.error);
    }

    @Override
    public int hashCode() {
        return Objects.hash(favorite, error, StatusCode);
    }
}

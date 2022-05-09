package platform.webapplication.models.Favorites;

import lombok.*;
import platform.webapplication.entities.Favorites;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class AllFavorites {
    private List<Favorites> favorites = new ArrayList<>();
    private String error = "";
    private int statusCode = 500;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AllFavorites that = (AllFavorites) o;
        return statusCode == that.statusCode && Objects.equals(favorites, that.favorites) && Objects.equals(error, that.error);
    }

    @Override
    public int hashCode() {
        return Objects.hash(favorites, error, statusCode);
    }
}



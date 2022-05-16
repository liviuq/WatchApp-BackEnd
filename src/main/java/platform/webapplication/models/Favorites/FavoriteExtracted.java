package platform.webapplication.models.Favorites;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class FavoriteExtracted {
    private List<FavoriteUtils> favoriteUtilsList = new ArrayList<>();
    private String error = "";
    private int StatusCode = 500;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavoriteExtracted that = (FavoriteExtracted) o;
        return StatusCode == that.StatusCode && Objects.equals(favoriteUtilsList, that.favoriteUtilsList) && Objects.equals(error, that.error);
    }

    @Override
    public int hashCode() {
        return Objects.hash(favoriteUtilsList, error, StatusCode);
    }
}

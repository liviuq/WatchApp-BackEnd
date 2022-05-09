package platform.webapplication.models.Favorites;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class FavoriteExtractedProduct {
    private FavoriteUtils favoriteUtils = new FavoriteUtils();
    private String error = "";
    private int StatusCode = 500;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavoriteExtractedProduct that = (FavoriteExtractedProduct) o;
        return StatusCode == that.StatusCode && Objects.equals(favoriteUtils, that.favoriteUtils) && Objects.equals(error, that.error);
    }

    @Override
    public int hashCode() {
        return Objects.hash(favoriteUtils, error, StatusCode);
    }
}

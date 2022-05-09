package platform.webapplication.models.Favorites;

import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class FavoriteExtractedProduct {
    private FavoriteUtils favoriteUtils = new FavoriteUtils();
    private String error = "";
    private int StatusCode = 500;
}

package platform.webapplication.models.Favorites;

import lombok.*;
import platform.webapplication.entities.Favorites;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class SingleFavorite {
    private Favorites favorite = new Favorites();
    private String error = "";
    private int StatusCode = 500;
}

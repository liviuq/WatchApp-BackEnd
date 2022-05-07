package platform.webapplication.models.Favorites;

import lombok.*;
import platform.webapplication.entities.Favorites;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class AllFavorites {
    private List<Favorites> favorites = new ArrayList<>();
    private String error = "";
    private int statusCode = 500;
}



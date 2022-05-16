package platform.webapplication.models.Favorites;


import lombok.*;

import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class FavoriteUtils {
    private Integer id;
    private String brand;
    private Float price;
    private String seller;
    private Integer product_id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavoriteUtils that = (FavoriteUtils) o;
        return Objects.equals(id, that.id) && Objects.equals(brand, that.brand) && Objects.equals(price, that.price) && Objects.equals(seller, that.seller);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand, price, seller);
    }
}

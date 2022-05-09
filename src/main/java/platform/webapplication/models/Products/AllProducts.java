package platform.webapplication.models.Products;

import lombok.*;
import platform.webapplication.entities.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class AllProducts {
    private List<Product> products = new ArrayList<Product>();
    private String error = "";
    private int statusCode = 500;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AllProducts that = (AllProducts) o;
        return statusCode == that.statusCode && Objects.equals(products, that.products) && Objects.equals(error, that.error);
    }

    @Override
    public int hashCode() {
        return Objects.hash(products, error, statusCode);
    }
}

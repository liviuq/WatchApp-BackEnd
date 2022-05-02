package platform.webapplication.models.Products;

import lombok.*;
import platform.webapplication.entities.Product;

import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class ProductUpdated {
    private Product product = new Product();
    private String error = "";
    private int statusCode = 500;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductUpdated that = (ProductUpdated) o;
        return statusCode == that.statusCode && Objects.equals(product, that.product) && Objects.equals(error, that.error);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, error, statusCode);
    }
}

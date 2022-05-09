package platform.webapplication.models.Products;

import lombok.*;

import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class ProductDeleted {
    private Integer productId;
    private String error = "";
    private int statusCode = 500;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDeleted that = (ProductDeleted) o;
        return statusCode == that.statusCode && Objects.equals(productId, that.productId) && Objects.equals(error, that.error);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, error, statusCode);
    }
}
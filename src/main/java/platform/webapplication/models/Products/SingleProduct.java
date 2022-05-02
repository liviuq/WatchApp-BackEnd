package platform.webapplication.models.Products;

import lombok.*;
import platform.webapplication.entities.Product;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class SingleProduct {
    private Product product = new Product();
    private String error = "";
    private int statusCode = 500;

}

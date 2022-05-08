package platform.webapplication.models.Products;

import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class ProductDeleted {
    private Integer productId;
    private String error = "";
    private int statusCode = 500;
}
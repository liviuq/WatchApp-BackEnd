package platform.webapplication.models.Cart;

import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class CartDeleted {
    private Integer cartId;
    private String error = "";
    private int statusCode = 500;
}
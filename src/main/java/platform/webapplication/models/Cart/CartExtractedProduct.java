package platform.webapplication.models.Cart;

import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class CartExtractedProduct {
    private CartUtils cartUtils = new CartUtils();
    private String error = "";
    private int StatusCode = 500;
}

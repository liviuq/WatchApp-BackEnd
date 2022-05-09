package platform.webapplication.models.Cart;

import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class CartTotalPrice {
    private CartExtracted cartExtracted = new CartExtracted();
    private Float totalPrice;
}

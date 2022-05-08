package platform.webapplication.models.Cart;

import lombok.*;
import platform.webapplication.entities.Cart;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class SingleCart {
    private Cart cart = new Cart();
    private String error = "";
    private int StatusCode = 500;
}
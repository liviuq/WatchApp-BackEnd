package platform.webapplication.models.Cart;

import lombok.*;
import platform.webapplication.entities.Cart;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class AllCart {
    private List<Cart> cart = new ArrayList<>();
    private String error = "";
    private int statusCode = 500;

}
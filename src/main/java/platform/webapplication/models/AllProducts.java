package platform.webapplication.models;

import lombok.*;
import platform.webapplication.enitites.Product;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class AllProducts {
    private List<Product> products = new ArrayList<Product>();
    private String error = "";
    private int statusCode = 500;

}

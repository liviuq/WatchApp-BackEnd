package platform.webapplication.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import platform.webapplication.model.Cart;
import platform.webapplication.model.Product;
import platform.webapplication.service.CartService;
import platform.webapplication.service.ProductService;

import java.util.List;

@RestController
@RequestMapping(path="/cart")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Integer id) {
        cartService.deleteById(id);
    }

    @GetMapping()
    public List<Cart> listCartProducts(){ return cartService.findAll(); }

    @GetMapping("{id}")
    public Cart listCart(@PathVariable Integer id){ return cartService.findById(id); }
}

package platform.webapplication.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import platform.webapplication.entities.Cart;
import platform.webapplication.entities.Product;
import platform.webapplication.models.Cart.*;
import platform.webapplication.service.CartService;
import platform.webapplication.service.ProductService;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200", "https://watchappa3.herokuapp.com"})
@RestController
@RequestMapping(path="/cart")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @DeleteMapping("{buyer_id}/delete/{id}")
    public CartDeleted delete(@PathVariable Integer buyer_id, @PathVariable Integer id) {
        return cartService.deleteById(buyer_id, id);
    }

    @GetMapping()
    public AllCart listCartProducts(){ return cartService.findAll(); }

//    @GetMapping("{buyer_id}/{id}")
//    public SingleCart listUserCartProduct(@PathVariable Integer buyer_id, @PathVariable Integer id){
//        return cartService.findByIdFromUser(buyer_id, id);
//    }

//    @GetMapping("{buyer_id}")
//    public AllCart listCart(@PathVariable Integer buyer_id){ return cartService.findUserCart(buyer_id); }

    @PostMapping("{buyer_id}/insert")
    public CartAdded add(@RequestBody Cart cart, @PathVariable Integer buyer_id){
        CartAdded result = cartService.saveToCart(cart, buyer_id);
        return result;
    }

    @GetMapping("{buyer_id}")
    public CartTotalPrice listExtractedCart(@PathVariable Integer buyer_id){
        return cartService.findCartExtracted(buyer_id);
    }

    @GetMapping("{buyer_id}/{id}")
    public CartExtractedProduct cartExtractedProduct(@PathVariable Integer buyer_id, @PathVariable Integer id){
        return cartService.findCartExtractedProduct(buyer_id,id);
    }

}

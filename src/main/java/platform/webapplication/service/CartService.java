package platform.webapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.webapplication.entities.*;
import platform.webapplication.models.Cart.AllCart;
import platform.webapplication.models.Cart.CartAdded;
import platform.webapplication.models.Cart.CartDeleted;
import platform.webapplication.models.Cart.SingleCart;
import platform.webapplication.models.Products.AllProducts;
import platform.webapplication.models.Users.AllUsers;
import platform.webapplication.repository.CartRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    private CartRepository cartRepository;
    private final ProductService productService;
    private final UserService userService;

    @Autowired
    public CartService(CartRepository cartRepository, ProductService productService, UserService userService){
        this.cartRepository = cartRepository;
        this.productService = productService;
        this.userService = userService;
    }
    public AllCart findAll() {

        var it = cartRepository.findAll();
        if(it.isEmpty()){
            return new AllCart(new ArrayList<Cart>(),"Cart is Empty",200);
        }

        var cart = new ArrayList<Cart>(it);
        return new AllCart(cart,"",200);
    }

    public CartAdded saveToCart(Cart cart, Integer pathId){
        Integer productId = cart.getProduct_id();
        Integer buyerId = cart.getBuyer_id();
        if(!cart.getBuyer_id().equals(pathId)) {
            System.out.println("Favorite product has a different user id than the path one.");
            return new CartAdded(new Cart(),"Favorite product has a different user id than the path one.",404);
        }

        AllCart cartProducts = findAll();
        for(Cart c : cartProducts.getCart()) {
            if(c.getProduct_id().equals(productId) && c.getBuyer_id().equals(buyerId)) {
                return new CartAdded(new Cart(),"Product already exists in Favorites List!",404);
            }
        }

        boolean validUser = false;
        boolean validProduct = false;
        AllProducts products = productService.findAll();
        for(Product p : products.getProducts()) {
            if(p.getId().equals(productId)) {
                validProduct = true;
                break;
            }
        }

        AllUsers users = userService.findAll();
        for(User u : users.getUsers()) {
            if(u.getId().equals(buyerId)) {
                validUser = true;
                break;
            }
        }

        if(!validProduct) {
            return new CartAdded(new Cart(),"You can't add to Favorite a product that is not in the Catalog!",404);
        }

        if(!validUser) {

            return new CartAdded(new Cart(),"Invalid user id for favorite product",404);
        }

        var result = cartRepository.save(cart);

        if(result == null) {
            return new CartAdded(new Cart(),"A aparut o eroare in cadrul adaugarii produsului la favorite!",500);
        }

        return new CartAdded(result,"",201);
    }

    public SingleCart findById(Integer id)
    {
        System.out.printf("Doamne ajuta\n");
        var allCart = cartRepository.findAll();
        for(Cart cart : allCart)
        {
            if(cart.getBuyer_id().equals(id))
            {
                return new SingleCart(cart, "", 200);
            }
        }
        return new SingleCart(new Cart(), "", 200);
    }

    public SingleCart findByIdFromUser(Integer buyer_id, Integer id) {
        var allCart = findUserCart(buyer_id);
        for(Cart cart : allCart.getCart())
        {
            if(cart.getId().equals(id))
            {
                return new SingleCart(cart, "", 200);
            }
        }
        return new SingleCart(new Cart(), "Product not found in user's cart", 200);
    }

    public Long count() {

        return cartRepository.count();
    }

    public CartDeleted deleteById(Integer buyer_id, Integer cartId) {
        SingleCart currentCart = this.findById(cartId);
        if(currentCart.getCart().getBuyer_id().equals(buyer_id))
        {
            cartRepository.deleteById(cartId);
            return new CartDeleted(cartId, "", 200);
        }
        else
        {
            return new CartDeleted(cartId, "Product does not belong to user", 200);
        }
    }

    public AllCart findUserCart(Integer buyer_id) {
        var allCart = cartRepository.findAll();
        List<Cart> returnedCart = new ArrayList<>();
        for(Cart cart : allCart)
        {
            if(cart.getBuyer_id().equals(buyer_id))
            {
                returnedCart.add(cart);
            }
        }
        return new AllCart(returnedCart, "", 200);
    }
}


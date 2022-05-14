package platform.webapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.webapplication.entities.*;
import platform.webapplication.models.Cart.*;
import platform.webapplication.models.Products.AllProducts;
import platform.webapplication.models.Products.SingleProduct;
import platform.webapplication.models.Users.AllUsers;
import platform.webapplication.models.Users.SingleUser;
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
            return new AllCart(new ArrayList<Cart>(),"Cart is Empty",204);
        }

        var cart = new ArrayList<Cart>(it);
        return new AllCart(cart,"",200);
    }

    public CartAdded saveToCart(Cart cart, Integer pathId){
        Integer productId = cart.getProduct_id();
        Integer buyerId = cart.getBuyer_id();
        if(!cart.getBuyer_id().equals(pathId)) {
            System.out.println("Cart product has a different user id than the path one.");
            return new CartAdded(new Cart(),"Cart product has a different user id than the path one.",404);
        }

        AllCart cartProducts = findAll();
        for(Cart c : cartProducts.getCart()) {
            if(c.getProduct_id().equals(productId) && c.getBuyer_id().equals(buyerId)) {
                return new CartAdded(new Cart(),"Product already exists in Cart List!",404);
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
            return new CartAdded(new Cart(),"You can't add to Cart a product that is not in the Catalog!",404);
        }

        if(!validUser) {

            return new CartAdded(new Cart(),"Invalid user id for cart product",404);
        }

        var result = cartRepository.save(cart);

        if(result == null) {
            return new CartAdded(new Cart(),"A aparut o eroare in cadrul adaugarii produsului in cos!",500);
        }

        return new CartAdded(result,"",201);
    }

    public CartAdded saveProductToCart(Integer buyerId, Integer productId){
        boolean validUser = false;
        boolean validProduct = false;
        Integer sellerId = 0;

        AllProducts products = productService.findAll();
        for(Product p : products.getProducts()) {
            if(p.getId().equals(productId)) {
                validProduct = true;
                sellerId = p.getUser_id();
                break;
            }
        }

        if(!validProduct) {
            return new CartAdded(new Cart(),"You can't add to Cart a product that is not in the Catalog!",404);
        }

        AllUsers users = userService.findAll();
        for(User u : users.getUsers()) {
            if(u.getId().equals(buyerId)) {
                validUser = true;
                break;
            }
        }

        if(!validUser) {

            return new CartAdded(new Cart(),"Invalid user id for cart product",404);
        }

        Cart cart = new Cart();
        cart.setProduct_id(productId);
        cart.setBuyer_id(buyerId);
        cart.setSeller_id(sellerId);

        var result = cartRepository.save(cart);

        if(result == null) {
            return new CartAdded(new Cart(),"A aparut o eroare in cadrul adaugarii produsului in cos!",500);
        }

        return new CartAdded(result,"",201);

    }

    public SingleCart findById(Integer id)
    {
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

    public AllCart findUserCart(Integer buyerId) {
        var allCart = cartRepository.findAll();
        List<Cart> returnedCart = new ArrayList<>();
        for(Cart cart : allCart)
        {
            if(cart.getBuyer_id().equals(buyerId))
            {
                returnedCart.add(cart);
            }
        }
        return new AllCart(returnedCart, "", 200);
    }

    public CartTotalPrice findCartExtracted(Integer buyerId){
        AllCart allCart = findUserCart(buyerId);
        List<CartUtils> cartUtilsList = new ArrayList<>();
        Float totalPrice = 0f;

        for(Cart cart : allCart.getCart()){
            SingleProduct singleProduct = productService.findById(cart.getProduct_id());
            SingleUser singleUser = userService.findById(buyerId);
            CartUtils cartUtils = new CartUtils();

            cartUtils.setId(cart.getId());
            cartUtils.setName(singleProduct.getProduct().getName());
            cartUtils.setPrice(singleProduct.getProduct().getPrice());
            cartUtils.setBuyer(singleUser.getUser().getUser_name());

            cartUtilsList.add(cartUtils);

            totalPrice+=cartUtils.getPrice();
        }
        return new CartTotalPrice(new CartExtracted(cartUtilsList,"",200),totalPrice);
    }

    public CartExtractedProduct findCartExtractedProduct(Integer buyerId, Integer cartId){
        CartTotalPrice cartTotalPrice = findCartExtracted(buyerId);
        for(CartUtils cartUtils : cartTotalPrice.getCartExtracted().getCartUtils()){
            if(cartUtils.getId().equals(cartId))
                return new CartExtractedProduct(cartUtils,"",200);
        }
        return new CartExtractedProduct(new CartUtils(),"Product not found in user's cart",200);
    }
}


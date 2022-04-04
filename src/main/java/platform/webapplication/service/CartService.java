package platform.webapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import platform.webapplication.model.Cart;
import platform.webapplication.repository.CartRepository;

import java.util.ArrayList;
import java.util.List;

public class CartService {

    private CartRepository cartRepository;

    @Autowired
    public CartService(CartRepository cartRepository){
        this.cartRepository = cartRepository;
    }
    public List<Cart> findAll() {

        var it = cartRepository.findAll();

        ArrayList<Cart> carts = new ArrayList<Cart>();
        it.forEach(e -> carts.add(e));

        return carts;
    }

    public Long count() {

        return cartRepository.count();
    }

    public void deleteById(Integer cartId) {
        cartRepository.deleteById(cartId);
    }
}
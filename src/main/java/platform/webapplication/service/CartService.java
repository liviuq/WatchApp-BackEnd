package platform.webapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.webapplication.entities.Cart;
import platform.webapplication.entities.Order;
import platform.webapplication.models.Cart.CartAdded;
import platform.webapplication.models.Cart.SingleCart;
import platform.webapplication.repository.CartRepository;

import java.util.ArrayList;
import java.util.List;

@Service
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

    public CartAdded addtoCart(Cart Cart, Integer pathId)
    {
        Integer productId = cart.getProduct_id();
        Integer userId = cart.getUser_id();
    }
    public SingleCart findById(Integer id)
    {
        var result = cartRepository.findById(id);
        if(result.isEmpty())
        {
            return new SingleCart(null,"Product not found",404);
        }
        SingleCart cart = new SingleCart(result.get(),"",200);
    }

    public Long count() {

        return cartRepository.count();
    }

    public void deleteById(Integer cartId) {
        cartRepository.deleteById(cartId);
    }

    public Cart saveToCart(Cart cart){
        return cartRepository.save(cart);
    }
}


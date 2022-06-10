package platform.webapplication.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import platform.webapplication.entities.Cart;
import platform.webapplication.models.Cart.*;
import platform.webapplication.models.Favorites.FavoriteAdded;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class CartControllerTest {

    public static RestTemplate restTemplate = new RestTemplate();

    @Test
    void add() {
        Cart cart = new Cart(1,2,42,42);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<Cart> entity = new HttpEntity<>(cart,httpHeaders);
        CartAdded cartAdded =  restTemplate.exchange("http://localhost:5000/cart/42/insert", HttpMethod.POST, entity, CartAdded.class).getBody();
        assertEquals(201, cartAdded.getStatusCode());
    }

    @Test
    void addCart() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        CartAdded cartAdded =  restTemplate.exchange("http://localhost:5000/cart/42/insert/2", HttpMethod.POST, entity, CartAdded.class).getBody();
        assertEquals(201, cartAdded.getStatusCode());
    }

    @Test
    void listExtractedCart() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        CartTotalPrice cartTotalPrice =  restTemplate.exchange("http://localhost:5000/cart/42", HttpMethod.GET, entity, CartTotalPrice.class).getBody();
        assertNotNull(cartTotalPrice);
        assertEquals(201, cartTotalPrice.getCartExtracted().getStatusCode());
    }

    @Test
    void cartExtractedProduct() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        CartExtractedProduct cartExtractedProduct =  restTemplate.exchange("http://localhost:5000/cart/42/1", HttpMethod.POST, entity, CartExtractedProduct.class).getBody();
        assertEquals(201, cartExtractedProduct.getStatusCode());
    }

    @Test
    void listCartProducts() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        AllCart allCart =  restTemplate.exchange("http://localhost:5000/cart", HttpMethod.GET, entity, AllCart.class).getBody();
        assertEquals(200, allCart.getStatusCode());
    }

    @Test
    void delete() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        CartDeleted cartDeleted =  restTemplate.exchange("http://localhost:5000/cart/42/delete/2", HttpMethod.DELETE, entity, CartDeleted.class).getBody();
        assertEquals(200, cartDeleted.getStatusCode());
    }
}
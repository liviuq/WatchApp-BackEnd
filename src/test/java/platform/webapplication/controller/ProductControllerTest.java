package platform.webapplication.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import platform.webapplication.entities.Product;
import platform.webapplication.models.Products.*;
import platform.webapplication.models.Users.AllUsers;
import platform.webapplication.models.Users.SingleUser;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductControllerTest {

    public static RestTemplate restTemplate = new RestTemplate();

    @Test
    void all() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        AllProducts products =  restTemplate.exchange("http://localhost:5000/product", HttpMethod.GET, entity, AllProducts.class).getBody();
        assertEquals(200, products.getStatusCode());
    }

    @Test
    void byId() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        SingleProduct product =  restTemplate.exchange("http://localhost:5000/product/2", HttpMethod.GET, entity, SingleProduct.class).getBody();
        assertEquals(200, product.getStatusCode());
    }

    @Test
    void categories() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        List<String> categories =  restTemplate.exchange("http://localhost:5000/product/product-categories", HttpMethod.GET, entity, List.class).getBody();
        assertNotEquals(0, categories.size());
    }

    @Test
    void add() {
        Product product = new Product(1, 42, "Test", 199f, (Date) null, 2001, "aaa", (String) null, "", (String) null, (String) null, (String) null, (String) null, (String) null, (byte) 0, (byte) 0, "", 0, (byte) 0, (byte) 0, (String) null, "", "");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<Product> entity = new HttpEntity<>(product, httpHeaders);
        ProductAdded productAdded =  restTemplate.exchange("http://localhost:5000/product/42", HttpMethod.POST, entity, ProductAdded.class).getBody();
        assertEquals(201, productAdded.getStatusCode());
    }

    @Test
    void update() {
        Product product = new Product(2, 42, "Test", 199f, (Date) null, 2001, "aaa", (String) null, "", (String) null, (String) null, (String) null, (String) null, (String) null, (byte) 0, (byte) 0, "", 0, (byte) 0, (byte) 0, (String) null, "", "");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<Product> entity = new HttpEntity<>(product, httpHeaders);
        ProductUpdated productUpdated =  restTemplate.exchange("http://localhost:5000/product/2", HttpMethod.PUT, entity, ProductUpdated.class).getBody();
        assertEquals(202, productUpdated.getStatusCode());
    }

    @Test
    void getUserProducts() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        AllProducts allProducts =  restTemplate.exchange("http://localhost:5000/product/42/products", HttpMethod.GET, entity, AllProducts.class).getBody();
        assertEquals(200, allProducts.getStatusCode());
    }

    @Test
    void getFilters() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        ProductFilters productFilters =  restTemplate.exchange("http://localhost:5000/product/filters", HttpMethod.GET, entity, ProductFilters.class).getBody();
        assertEquals(200, productFilters.getStatusCode());
    }

    @Test
    void searchedProducts() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        HashSet<Product> products =  restTemplate.exchange("http://localhost:5000/product/search?brand=&category=clasic&price=59.99", HttpMethod.GET, entity, HashSet.class).getBody();
        assertNotNull(products);
    }

    @Test
    void searchedProductsPaged() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        AllProducts products =  restTemplate.exchange("http://localhost:5000/product/search/1", HttpMethod.GET, entity, AllProducts.class).getBody();
        assertEquals(200, products.getStatusCode());
    }

    @Test
    void listPromoted() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        AllProducts products =  restTemplate.exchange("http://localhost:5000/product/promoted/1", HttpMethod.GET, entity, AllProducts.class).getBody();
        assertEquals(200, products.getStatusCode());
    }
}
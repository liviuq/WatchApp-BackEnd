package platform.webapplication.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import platform.webapplication.models.Favorites.*;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class FavoritesControllerTest {

    public static RestTemplate restTemplate = new RestTemplate();

    @Test
    void all() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        AllFavorites favorites =  restTemplate.exchange("http://localhost:5000/favorites/list/42", HttpMethod.GET, entity, AllFavorites.class).getBody();
        assertEquals(200, favorites.getStatusCode());
    }

    @Test
    void extract() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        FavoriteExtracted favorites =  restTemplate.exchange("http://localhost:5000/favorites/extract/42", HttpMethod.GET, entity, FavoriteExtracted.class).getBody();
        assertEquals(200, favorites.getStatusCode());
    }

    @Test
    void extractedProduct() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        FavoriteExtractedProduct favorites =  restTemplate.exchange("http://localhost:5000/favorites/extract/42/2", HttpMethod.GET, entity, FavoriteExtractedProduct.class).getBody();
        assertEquals(200, favorites.getStatusCode());
    }

    @Test
    void add() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        FavoriteAdded favorites =  restTemplate.exchange("http://localhost:5000/favorites/42/insert/2", HttpMethod.POST, entity, FavoriteAdded.class).getBody();
        assertEquals(200, favorites.getStatusCode());
    }

    @Test
    void delete() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        FavoriteDeleted favorites =  restTemplate.exchange("http://localhost:5000/favorites/42/delete/2", HttpMethod.DELETE, entity, FavoriteDeleted.class).getBody();
        assertEquals(200, favorites.getStatusCode());
    }
}
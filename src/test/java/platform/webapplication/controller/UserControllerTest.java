package platform.webapplication.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;
import platform.webapplication.entities.User;
import platform.webapplication.models.Users.AllUsers;
import platform.webapplication.models.Users.SingleUser;
import platform.webapplication.models.Users.UserUpdated;

import java.util.Collection;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {

    public static RestTemplate restTemplate = new RestTemplate();

    @Test
    void all() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        AllUsers users =  restTemplate.exchange("http://localhost:5000/user", HttpMethod.GET, entity, AllUsers.class).getBody();
        assertEquals(200, users.getStatusCode());
    }

    @Test
    void byId() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        SingleUser user =  restTemplate.exchange("http://localhost:5000/user/42", HttpMethod.GET, entity, SingleUser.class).getBody();
        assertEquals(200, user.getStatusCode());
    }

    @Test
    void update() {
        User user = new User(111, "username_test", "password_test", "Firstname_test", "Lastname_test", "test@yahoo.com", null, null, null, null, null, "", null);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<User> entity = new HttpEntity<>(user, httpHeaders);
        UserUpdated userUpdated =  restTemplate.exchange("http://localhost:5000/user/42", HttpMethod.PUT, entity, UserUpdated.class).getBody();
        assertEquals(202, userUpdated.getStatusCode());
    }

    @Test
    void updatedPhoneNumber() {
        User user = new User(111, "usernametest", "passwordtest", "Firstname_test", "Lastname_test", "test@yahoo.com", null, null, null, null, null, "1111111", null);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<User> entity = new HttpEntity<>(user, httpHeaders);
        UserUpdated userUpdated =  restTemplate.exchange("http://localhost:5000/user/42/phone", HttpMethod.PUT, entity, UserUpdated.class).getBody();
        assertEquals(202, userUpdated.getStatusCode());
    }

    @Test
    void updateUserData() {
        User user = new User(111, "usernametest", "passwordtest", "Firstname_test", "Lastname_test", "test@yahoo.com", null, null, null, null, null, "1111111", null);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<User> entity = new HttpEntity<>(user, httpHeaders);
        UserUpdated userUpdated =  restTemplate.exchange("http://localhost:5000/user/42/update/userdata", HttpMethod.PUT, entity, UserUpdated.class).getBody();
        assertEquals(202, userUpdated.getStatusCode());
    }
}
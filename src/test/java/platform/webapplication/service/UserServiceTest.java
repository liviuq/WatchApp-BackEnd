package platform.webapplication.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import platform.webapplication.entities.User;
import platform.webapplication.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private UserRepository userRepository;
    private UserService userService;
    private List<User> users;
    private User user;

    @BeforeEach
    void setUp() {
        users = new ArrayList<>();
        user = new User("Popescu", "Vasile");
        this.userRepository = mock(UserRepository.class);
        when(userRepository.findAll()).thenReturn(users);
        when(userRepository.findById(anyInt())).thenReturn(Optional.ofNullable(user));
        doNothing().when(userRepository).deleteById(anyInt());
        when(userRepository.save(user)).thenReturn(user);

        this.userService = new UserService(this.userRepository);

    }

    @Test
    void findAll() {
        //Arrange
        users.add(new User("Popescu", "Ion"));
        users.add(new User("Popa", "Gigel"));
        users.add(new User("Popescu", "Vasile"));
        users.add(new User("Popescu", "Andrei"));

        //Act
        var actual = userService.findAll();

        //Assert
        assertEquals(200, actual.getStatusCode());
        assertEquals("", actual.getError());
        assertEquals(users, actual.getUsers());
    }

    @Test
    void findById() {
        //Arrange
        User expected = new User("Popescu", "Vasile");

        //Act
        var actual = userService.findById(anyInt());

        //Assert
        assertEquals(200, actual.getStatusCode());
        assertEquals("", actual.getError());
    }

    @Test
    void deleteById() {
        //Act
        userService.deleteById(anyInt());
        //Assert
        verify(userRepository, times(1)).deleteById(anyInt());
    }

    @Test
    void update() {
        //Act
        var actual = userService.update(anyInt(), user);

        //Assert
        assertEquals(202, actual.getStatusCode());
        assertEquals("", actual.getError());
        assertEquals(user, actual.getUser());
    }

    @Test
    void updatePhone() {
        //Act
        var actual = userService.update(anyInt(), user);

        //Assert
        assertEquals(202, actual.getStatusCode());
        assertEquals("", actual.getError());
        assertEquals(user, actual.getUser());
    }

    @Test
    void updateUserdata() {
        //Act
        var actual = userService.update(anyInt(), user);

        //Assert
        assertEquals(202, actual.getStatusCode());
        assertEquals("", actual.getError());
        assertEquals(user, actual.getUser());
    }
}
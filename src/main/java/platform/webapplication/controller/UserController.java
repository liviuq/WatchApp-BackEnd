package platform.webapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import platform.webapplication.model.User;
import platform.webapplication.repository.UserRepository;
import platform.webapplication.service.UserService;

import java.util.List;

@RestController
@RequestMapping(path="/user")
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping()
    public List<User> allUsers() {

        return userService.findAll();
    }

    @GetMapping("{id}")
    public User userById(@PathVariable Integer id) {

        return userService.findById(id);
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Integer id) {
        userService.deleteById(id);
    }

    @PostMapping("register")
    public User newUser(@RequestBody User user) {
        return userRepository.save(user);
    }
}

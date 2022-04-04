package platform.webapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import platform.webapplication.model.User;
import platform.webapplication.service.UserService;

import java.util.List;

@RestController
@RequestMapping(path="/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
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
}
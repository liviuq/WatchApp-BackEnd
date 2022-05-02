package platform.webapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import platform.webapplication.models.UserUpdated;
import platform.webapplication.entities.User;
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
    public List<User> all() {

        return userService.findAll();
    }

    @GetMapping("{id}")
    public User byId(@PathVariable Integer id) {

        return userService.findById(id);
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Integer id) {
        userService.deleteById(id);
    }

    @PutMapping("{id}")
    public UserUpdated update(@RequestHeader Integer id, @RequestBody User user) {
        UserUpdated result = userService.update(id, user);
        return result;
    }
}

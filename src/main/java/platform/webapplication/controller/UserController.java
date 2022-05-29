package platform.webapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import platform.webapplication.models.Users.AllUsers;
import platform.webapplication.models.Users.SingleUser;
import platform.webapplication.models.Users.UserUpdated;
import platform.webapplication.entities.User;
import platform.webapplication.service.UserService;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(path="/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public AllUsers all() {
        AllUsers result = userService.findAll();
        return result;
    }

    @GetMapping("{id}")
    public SingleUser byId(@PathVariable Integer id) {

        SingleUser result = userService.findById(id);
        return result;
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Integer id) {
        userService.deleteById(id);
    }

    @PutMapping("{id}")
    public UserUpdated update(@PathVariable Integer id, @RequestBody User user) {
        UserUpdated result = userService.update(id, user);
        return result;
    }

    @PutMapping("{id}/phone")
    public UserUpdated updatedPhoneNumber(@PathVariable Integer id, @RequestBody User user) {
        UserUpdated result = userService.updatePhone(id, user);

        return result;
    }

    @PutMapping("{id}/update/userdata")
    public UserUpdated updateUserData(@PathVariable Integer id, @RequestBody User user) {
        UserUpdated result = userService.updateUserdata(id, user);

        return  result;
    }
}

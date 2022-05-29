package platform.webapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.webapplication.models.Users.AllUsers;
import platform.webapplication.models.Users.SingleUser;
import platform.webapplication.models.Users.UserUpdated;
import platform.webapplication.entities.User;
import platform.webapplication.repository.UserRepository;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public AllUsers findAll() {
        var it = userRepository.findAll();

        if(it.isEmpty())
        {
            return new AllUsers(new ArrayList<User>(), "", 204);
        }
        var users = new ArrayList<User>();
        it.forEach(e -> users.add(e));

        return new AllUsers(users, "", 200);
    }

    public SingleUser findById(Integer id) {

        var result = userRepository.findById(id);

        if(result.isEmpty()){
            return new SingleUser(null, "User not found", 404);
        }
        SingleUser user = new SingleUser(result.get(), "", 200);

        return user;
    }

    public Long count() {

        return userRepository.count();
    }

    public void deleteById(Integer userId) {
        userRepository.deleteById(userId);
    }

    public UserUpdated update(Integer id, User user) {
        UserUpdated userUpdated = new UserUpdated();
        Optional<User> result = userRepository.findById(id);

        if (result.isEmpty()) {
            userUpdated.setError("User not found");
            userUpdated.setStatusCode(404);

            return userUpdated;
        } else {
            User entity = result.get();
            Integer identifier = entity.getId();
            entity = user;
            entity.setId(identifier);

            userUpdated.setUser(userRepository.save(entity));
            userUpdated.setStatusCode(202);

            return userUpdated;
        }
    }

    public UserUpdated updatePhone(Integer id, User user) {
        var result = userRepository.findById(id);
        UserUpdated userUpdated = new UserUpdated();

        if (result.isEmpty()) {
            userUpdated.setError("User not found");
            userUpdated.setStatusCode(404);

            return userUpdated;
        }
        else {
            User entity = result.get();
            entity.setPhone_number(user.getPhone_number());

            userUpdated.setUser(userRepository.save(entity));
            userUpdated.setStatusCode(202);

            return userUpdated;
        }
    }

    public UserUpdated updateUserdata(Integer id, User user) {
        var result = userRepository.findById(id);
        UserUpdated userUpdated = new UserUpdated();

        if (result.isEmpty()) {
            userUpdated.setError("User not found");
            userUpdated.setStatusCode(404);

            return userUpdated;
        }
        else {
            User entity = result.get();
            entity.setCity(user.getCity());
            entity.setCounty(user.getCounty());
            entity.setAddress(user.getAddress());
            entity.setPostal_code(user.getPostal_code());

            userUpdated.setUser(userRepository.save(entity));
            userUpdated.setStatusCode(202);

            return userUpdated;
        }
    }
}
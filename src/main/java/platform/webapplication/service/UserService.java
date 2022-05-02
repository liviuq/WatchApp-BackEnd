package platform.webapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.webapplication.models.UserUpdated;
import platform.webapplication.entities.User;
import platform.webapplication.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {

        var it = userRepository.findAll();

        var users = new ArrayList<User>();
        it.forEach(e -> users.add(e));

        return users;
    }

    public User findById(Integer id) {

        var user = userRepository.findById(id);
        return user.orElse(null);
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
            userUpdated.setError("Product not found");
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
}
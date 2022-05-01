package platform.webapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.webapplication.model.User;
import platform.webapplication.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

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
}
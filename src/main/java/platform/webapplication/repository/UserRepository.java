package platform.webapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import platform.webapplication.entities.User;

@Repository
public interface  UserRepository extends JpaRepository<User, Integer> {
}

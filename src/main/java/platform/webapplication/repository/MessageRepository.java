package platform.webapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import platform.webapplication.model.Message;
import platform.webapplication.model.User;

public interface MessageRepository extends JpaRepository<Message, Integer> {
}

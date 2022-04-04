package platform.webapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import platform.webapplication.model.Message;
import platform.webapplication.model.User;

public interface MessageRepository extends CrudRepository<Message, Integer> {
}

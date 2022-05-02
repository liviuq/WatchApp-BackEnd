package platform.webapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import platform.webapplication.entities.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
}

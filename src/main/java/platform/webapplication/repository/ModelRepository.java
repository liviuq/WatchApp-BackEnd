package platform.webapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import platform.webapplication.entities.Models;

public interface ModelRepository extends JpaRepository<Models, Integer> {
}

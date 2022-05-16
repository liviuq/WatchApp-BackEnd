package platform.webapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import platform.webapplication.entities.Brand;
import platform.webapplication.entities.Model;

public interface ModelRepository extends JpaRepository<Model, Integer> {
}

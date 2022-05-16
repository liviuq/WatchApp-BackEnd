package platform.webapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import platform.webapplication.entities.Brand;
import platform.webapplication.entities.Mechanism;

public interface MechanismRepository extends JpaRepository<Mechanism, Integer> {
}

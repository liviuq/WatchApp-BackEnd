package platform.webapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import platform.webapplication.entities.Mechanisms;

public interface MechanismRepository extends JpaRepository<Mechanisms, Integer> {
}

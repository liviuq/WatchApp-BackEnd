package platform.webapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import platform.webapplication.entities.Brand;
import platform.webapplication.entities.Carcase;

public interface CarcaseRepository extends JpaRepository<Carcase, Integer> {
}

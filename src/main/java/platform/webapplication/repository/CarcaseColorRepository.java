package platform.webapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import platform.webapplication.entities.Brand;
import platform.webapplication.entities.CarcaseColor;

public interface CarcaseColorRepository extends JpaRepository<CarcaseColor, Integer> {
}

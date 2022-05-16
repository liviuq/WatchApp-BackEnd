package platform.webapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import platform.webapplication.entities.CarcaseColors;

public interface CarcaseColorRepository extends JpaRepository<CarcaseColors, Integer> {
}

package platform.webapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import platform.webapplication.entities.CarcaseThicknesses;

public interface CarcaseThicknessRepository extends JpaRepository<CarcaseThicknesses, Integer> {
}

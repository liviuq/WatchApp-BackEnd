package platform.webapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import platform.webapplication.entities.Brand;
import platform.webapplication.entities.CarcaseThickness;

public interface CarcaseThicknessRepository extends JpaRepository<CarcaseThickness, Integer> {
}

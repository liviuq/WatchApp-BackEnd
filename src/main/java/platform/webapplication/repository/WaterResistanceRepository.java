package platform.webapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import platform.webapplication.entities.Brand;
import platform.webapplication.entities.WaterResistence;

public interface WaterResistanceRepository extends JpaRepository<WaterResistence, Integer> {
}

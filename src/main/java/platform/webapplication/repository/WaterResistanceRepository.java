package platform.webapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import platform.webapplication.entities.Water_resistances;

public interface WaterResistanceRepository extends JpaRepository<Water_resistances, Integer> {
}

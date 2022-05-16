package platform.webapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import platform.webapplication.entities.StrapColors;

public interface StrapColorRepository extends JpaRepository<StrapColors, Integer> {
}

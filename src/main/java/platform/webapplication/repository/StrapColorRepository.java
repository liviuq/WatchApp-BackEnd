package platform.webapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import platform.webapplication.entities.Brand;
import platform.webapplication.entities.StrapColor;

public interface StrapColorRepository extends JpaRepository<StrapColor, Integer> {
}

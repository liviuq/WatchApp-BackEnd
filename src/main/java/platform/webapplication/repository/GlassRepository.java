package platform.webapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import platform.webapplication.entities.Brand;
import platform.webapplication.entities.Glass;

public interface GlassRepository extends JpaRepository<Glass, Integer> {
}

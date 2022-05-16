package platform.webapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import platform.webapplication.entities.Glasses;

public interface GlassRepository extends JpaRepository<Glasses, Integer> {
}

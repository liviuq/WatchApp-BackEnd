package platform.webapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import platform.webapplication.entities.Years;

public interface YearRepository extends JpaRepository<Years, Integer> {
}

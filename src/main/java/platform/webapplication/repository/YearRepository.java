package platform.webapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import platform.webapplication.entities.Brand;
import platform.webapplication.entities.Year;

public interface YearRepository extends JpaRepository<Year, Integer> {
}

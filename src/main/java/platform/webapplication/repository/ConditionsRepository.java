package platform.webapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import platform.webapplication.entities.Brands;
import platform.webapplication.entities.Conditions;

public interface ConditionsRepository extends JpaRepository<Conditions, Integer> {
}

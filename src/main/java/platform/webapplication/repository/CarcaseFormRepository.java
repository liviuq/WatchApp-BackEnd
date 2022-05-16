package platform.webapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import platform.webapplication.entities.CarcaseForms;

public interface CarcaseFormRepository extends JpaRepository<CarcaseForms, Integer> {
}

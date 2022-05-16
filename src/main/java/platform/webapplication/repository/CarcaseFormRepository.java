package platform.webapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import platform.webapplication.entities.Brand;
import platform.webapplication.entities.CarcaseForm;

public interface CarcaseFormRepository extends JpaRepository<CarcaseForm, Integer> {
}

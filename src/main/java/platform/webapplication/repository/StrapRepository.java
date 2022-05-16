package platform.webapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import platform.webapplication.entities.Brand;
import platform.webapplication.entities.Strap;

public interface StrapRepository extends JpaRepository<Strap, Integer> {
}

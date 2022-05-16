package platform.webapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import platform.webapplication.entities.Carcases;

public interface CarcaseRepository extends JpaRepository<Carcases, Integer> {
}

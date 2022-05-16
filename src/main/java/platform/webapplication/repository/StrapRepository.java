package platform.webapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import platform.webapplication.entities.Straps;

public interface StrapRepository extends JpaRepository<Straps, Integer> {
}

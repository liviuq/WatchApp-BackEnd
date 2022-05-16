package platform.webapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import platform.webapplication.entities.Gender;

public interface GenderRepository extends JpaRepository<Gender, Integer> {
}

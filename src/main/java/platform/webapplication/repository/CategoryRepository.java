package platform.webapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import platform.webapplication.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}

package platform.webapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import platform.webapplication.entities.Categories;

public interface CategoryRepository extends JpaRepository<Categories, Integer> {
}

package platform.webapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import platform.webapplication.model.Favorites;
import platform.webapplication.model.User;

public interface FavoritesRepository extends JpaRepository<Favorites, Integer> {
}

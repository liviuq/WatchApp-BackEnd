package platform.webapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import platform.webapplication.model.Favorites;
import platform.webapplication.model.User;

public interface FavoritesRepository extends CrudRepository<Favorites, Integer> {
}

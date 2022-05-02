package platform.webapplication.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import platform.webapplication.entities.Favorites;

@Repository
public interface FavoritesRepository extends CrudRepository<Favorites, Integer> {
}

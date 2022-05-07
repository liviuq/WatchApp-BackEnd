package platform.webapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import platform.webapplication.entities.Favorites;

@Repository
public interface FavoritesRepository extends JpaRepository<Favorites, Integer> {
}

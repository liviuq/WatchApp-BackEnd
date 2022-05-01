package platform.webapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.webapplication.enitites.Favorites;
import platform.webapplication.repository.FavoritesRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class FavoritesService {
    private FavoritesRepository favoritesRepository;

    @Autowired
    public FavoritesService(FavoritesRepository favoritesRepository) {
        this.favoritesRepository = favoritesRepository;
    }

    public List<Favorites> findAll() {

        var it = favoritesRepository.findAll();

        ArrayList<Favorites> favorites = new ArrayList<Favorites>();
        it.forEach(e -> favorites.add(e));

        return favorites;
    }

    public Long count() {

        return favoritesRepository.count();
    }

    public void deleteById(Integer favoriteId) {
        favoritesRepository.deleteById(favoriteId);
    }
}

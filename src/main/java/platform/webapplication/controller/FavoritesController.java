package platform.webapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import platform.webapplication.model.Favorites;
import platform.webapplication.repository.FavoritesRepository;
import platform.webapplication.service.FavoritesService;

@RestController
@RequestMapping(path="/favorites")
public class FavoritesController {
    private final FavoritesService favoritesService;
    private final FavoritesRepository favoritesRepository;

    @Autowired
    public FavoritesController(FavoritesService favoritesService, FavoritesRepository favoritesRepository) {
        this.favoritesService = favoritesService;
        this.favoritesRepository = favoritesRepository;
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Integer id) {
        favoritesService.deleteById(id);
    }

    @PostMapping()
    public Favorites addProductToFavorites(@RequestBody Favorites favorite) {
        return favoritesRepository.save(favorite);
    }
}

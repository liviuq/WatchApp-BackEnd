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

    @Autowired
    public FavoritesController(FavoritesService favoritesService) {
        this.favoritesService = favoritesService;
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Integer id) {
        favoritesService.deleteById(id);
    }

    @PostMapping()
    public Favorites addProductToFavorites(@RequestBody Favorites favorite) {
        return favoritesService.saveProduct(favorite);
    }
}

package platform.webapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import platform.webapplication.entities.Favorites;
import platform.webapplication.models.Favorites.AllFavorites;
import platform.webapplication.models.Favorites.FavoriteAdded;
import platform.webapplication.service.FavoritesService;

import javax.validation.Valid;

@RestController
@RequestMapping(path="/favorites")
public class FavoritesController {
    private final FavoritesService favoritesService;
    @Autowired
    public FavoritesController(FavoritesService favoritesService) {
        this.favoritesService = favoritesService;
    }

    @GetMapping("list/{userId}")
    public AllFavorites all(@PathVariable Integer userId) {
        return favoritesService.findAllUserId(userId);
    }

    @PostMapping("insert/{userId}")
    public FavoriteAdded add(@Valid @RequestBody Favorites favorite, @PathVariable Integer userId) {
        FavoriteAdded result = favoritesService.addFav(favorite, userId);
        return result;
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Integer id) {
        favoritesService.deleteById(id);
    }
}
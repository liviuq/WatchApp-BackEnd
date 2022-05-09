package platform.webapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import platform.webapplication.entities.Favorites;
import platform.webapplication.models.Favorites.AllFavorites;
import platform.webapplication.models.Favorites.FavoriteAdded;
import platform.webapplication.models.Favorites.FavoriteExtracted;
import platform.webapplication.models.Favorites.FavoriteExtractedProduct;
import platform.webapplication.service.FavoritesService;

import javax.validation.Valid;

@CrossOrigin(origins = {"http://localhost:4200", "https://watchappa3.herokuapp.com"})
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

    @GetMapping("extract/{userId}")
    public FavoriteExtracted exctract(@PathVariable Integer userId) {
        return favoritesService.findFavoriteExtract(userId);
    }

    @GetMapping("extract/{userId}/{productId}")
    public FavoriteExtractedProduct extractedProduct(@PathVariable Integer userId, @PathVariable Integer productId) {
        return favoritesService.findFavoriteExtractProduct(userId, productId);
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

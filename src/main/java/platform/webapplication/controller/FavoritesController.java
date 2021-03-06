package platform.webapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import platform.webapplication.models.Favorites.*;
import platform.webapplication.service.FavoritesService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
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
    public FavoriteExtracted extract(@PathVariable Integer userId) {
        return favoritesService.findFavoriteExtract(userId);
    }

    @GetMapping("extract/{userId}/{productId}")
    public FavoriteExtractedProduct extractedProduct(@PathVariable Integer userId, @PathVariable Integer productId) {
        return favoritesService.findFavoriteExtractProduct(userId, productId);
    }

    @PostMapping("{userId}/insert/{productId}")
    public FavoriteAdded add(@PathVariable Integer userId, @PathVariable Integer productId) {
        FavoriteAdded result = favoritesService.addFav(userId, productId);
        return result;
    }

    @DeleteMapping("{userId}/delete/{id}")
    public FavoriteDeleted delete(@PathVariable Integer userId, @PathVariable Integer id) {
        return favoritesService.deleteById(userId, id);
    }
}

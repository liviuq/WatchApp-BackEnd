package platform.webapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import platform.webapplication.model.Favorites;
import platform.webapplication.repository.FavoritesRepository;
import platform.webapplication.service.FavoritesService;

import javax.validation.Valid;
import java.util.List;

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

    @PostMapping("insert/{userId}")
    public Favorites addProductToFavorites(@Valid @RequestBody Favorites favorite, @PathVariable Integer userId) {
        if(favorite.getUser_id().equals(userId)) {
            return favoritesService.saveProduct(favorite);
        }
        else {
            System.out.println("Favorite product has a different user id than the path one.");
            return favorite;
        }
    }

    @GetMapping("list/{userId}")
    public List<Favorites> listFavorites(@PathVariable Integer userId){
        List<Favorites> favorites = favoritesService.findAllUserId(userId);
        return favorites;
    }
}

package platform.webapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.webapplication.model.Favorites;
import platform.webapplication.model.Product;
import platform.webapplication.model.User;
import platform.webapplication.repository.FavoritesRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class FavoritesService {
    private FavoritesRepository favoritesRepository;

    @Autowired
    public FavoritesService(FavoritesRepository favoritesRepository, ProductService productService, UserService userService) {
        this.favoritesRepository = favoritesRepository;
        this.productService = productService;
        this.userService = userService;
    }

    private final ProductService productService;
    private final UserService userService;

    public List<Favorites> findAll() {

        var it = favoritesRepository.findAll();

        ArrayList<Favorites> favorites = new ArrayList<>();
        it.forEach(e -> favorites.add(e));

        return favorites;
    }

    public Long count() {
        return favoritesRepository.count();
    }

    public Favorites saveProduct(Favorites favorite) {
        Integer productId = favorite.getProduct_id();
        Integer userId = favorite.getUser_id();

        List<Favorites> favProducts = findAll();
        for(Favorites fav : favProducts) {
            if(fav.getProduct_id().equals(productId) && fav.getUser_id().equals(userId)) {
                System.out.println("Product already exists in Favorites List!");
                return favorite;
            }
        }

        boolean validUser = false, validProduct = false;

        List<Product> products = productService.findAll();
        for(Product p : products) {
            if(p.getId().equals(productId)) {
                validProduct = true;
                break;
            }
        }
        List<User> users = userService.findAll();
        for(User u : users) {
            if(u.getId().equals(userId)) {
                validUser = true;
                break;
            }
        }

        if(!validProduct) {
            System.out.println("You can't add to Favorite a product that is not in the Catalog!");
            return favorite;
        }

        if(!validUser) {
            System.out.println("Invalid user id for favorite product");
            return favorite;
        }
        return favoritesRepository.save(favorite);
    }

    public void deleteById(Integer favoriteId) {
        List<Favorites> favProducts = findAll();
        for(Favorites fav : favProducts) {
            if(fav.getId().equals(favoriteId)) {
                favoritesRepository.deleteById(favoriteId);
                return;
            }
        }
        System.out.println("Product with id " + favoriteId + " is not in Favorites list");
    }
}

package platform.webapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.webapplication.entities.Favorites;
import platform.webapplication.entities.Product;
import platform.webapplication.entities.User;
import platform.webapplication.models.Favorites.*;
import platform.webapplication.models.Products.AllProducts;
import platform.webapplication.models.Products.SingleProduct;
import platform.webapplication.models.Users.AllUsers;
import platform.webapplication.models.Users.SingleUser;
import platform.webapplication.repository.FavoritesRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class FavoritesService {
    private FavoritesRepository favoritesRepository;
    private final ProductService productService;
    private final UserService userService;

    @Autowired
    public FavoritesService(FavoritesRepository favoritesRepository, ProductService productService, UserService userService) {
        this.favoritesRepository = favoritesRepository;
        this.productService = productService;
        this.userService = userService;
    }

    public AllFavorites findAll() {

        var it = favoritesRepository.findAll();
        if(it.isEmpty()) {
            return new AllFavorites(new ArrayList<Favorites>(), "", 200);
        }

        var favorites = new ArrayList<Favorites>(it);
        return new AllFavorites(favorites, "", 200);
    }

    public AllFavorites findAllUserId(Integer userId) {

        var it = favoritesRepository.findAll();

        List<Favorites> favorites = new ArrayList<>();
        for(Favorites fav : it) {
            if(fav.getUser_id().equals(userId)) {
                favorites.add(fav);
            }
        }

        if(it.isEmpty()) {
            return new AllFavorites(new ArrayList<>(), "", 200);
        }
        var allFavorites = new AllFavorites(favorites, "", 200);

        return allFavorites;
    }

    public FavoriteExtracted findFavoriteExtract(Integer userId) {

        var it = favoritesRepository.findAll();

        List<Favorites> favorites = new ArrayList<>();

        for(Favorites fav : it) {
            if(fav.getUser_id().equals(userId)) {
                favorites.add(fav);
            }
        }

        if(it.isEmpty()) {
            return new FavoriteExtracted(new ArrayList<>(), "", 200);
        }

        List<FavoriteUtils> favoriteUtilsList = new ArrayList<>();
        for(Favorites fav : favorites) {

            SingleProduct singleProduct = productService.findById(fav.getProduct_id());
            SingleUser singleUser = userService.findById(singleProduct.getProduct().getUser_id());

            FavoriteUtils favoriteUtils = new FavoriteUtils();

            favoriteUtils.setId(fav.getId());
            favoriteUtils.setName(singleProduct.getProduct().getName());
            favoriteUtils.setPrice(singleProduct.getProduct().getPrice());
            favoriteUtils.setSeller(singleUser.getUser().getUser_name());

            favoriteUtilsList.add(favoriteUtils);
        }

        FavoriteExtracted extractedList = new FavoriteExtracted(favoriteUtilsList, "", 200);

        return extractedList;
    }

    public SingleFavorite findById(Integer id) {
        var result = favoritesRepository.findById(id);
        if(result.isEmpty()) {
            return new SingleFavorite(null, "Favorite Product not found", 404);
        }

        SingleFavorite favorite = new SingleFavorite(result.get(), "", 200);

        return favorite;
    }

    public FavoriteAdded addFav(Favorites favorite, Integer pathId) {
        Integer productId = favorite.getProduct_id();
        Integer userId = favorite.getUser_id();
        FavoriteAdded favoriteAdded = new FavoriteAdded();

        if(!favorite.getUser_id().equals(pathId)) {
            System.out.println("Favorite product has a different user id than the path one.");

            favoriteAdded.setError("Favorite product has a different user id than the path one.");
            favoriteAdded.setStatusCode(404);

            return favoriteAdded;
        }

        AllFavorites favProducts = findAll();
        for(Favorites fav : favProducts.getFavorites()) {
            if(fav.getProduct_id().equals(productId) && fav.getUser_id().equals(userId)) {
                favoriteAdded.setError("Product already exists in Favorites List!");
                favoriteAdded.setStatusCode(404);
                return favoriteAdded;
            }
        }

        boolean validUser = false, validProduct = false;
        AllProducts products = productService.findAll();
        for(Product p : products.getProducts()) {
            if(p.getId().equals(productId)) {
                validProduct = true;
                break;
            }
        }

        AllUsers users = userService.findAll();
        for(User u : users.getUsers()) {
            if(u.getId().equals(userId)) {
                validUser = true;
                break;
            }
        }

        if(!validProduct) {
            favoriteAdded.setError("You can't add to Favorite a product that is not in the Catalog!");
            favoriteAdded.setStatusCode(404);
            return favoriteAdded;
        }

        if(!validUser) {
            favoriteAdded.setError("Invalid user id for favorite product");
            favoriteAdded.setStatusCode(404);
            return favoriteAdded;
        }

        var result = favoritesRepository.save(favorite);

        if(result == null) {
            favoriteAdded.setError("A aparut o eroare in cadrul adaugarii produsului la favorite!");
            favoriteAdded.setStatusCode(500);
            return favoriteAdded;
        }

        favoriteAdded.setFavorite(result);
        favoriteAdded.setError("");
        favoriteAdded.setStatusCode(201);

        return favoriteAdded;
    }



    public void deleteById(Integer favoriteId) {
        favoritesRepository.deleteById(favoriteId);
    }

    public Long count() {

        return favoritesRepository.count();
    }

}

package platform.webapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.webapplication.entities.Favorites;
import platform.webapplication.entities.Product;
import platform.webapplication.entities.User;
import platform.webapplication.models.Cart.CartDeleted;
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
        if (it.isEmpty()) {
            return new AllFavorites(new ArrayList<Favorites>(), "", 204);
        }

        var favorites = new ArrayList<Favorites>(it);
        return new AllFavorites(favorites, "", 200);
    }

    public AllFavorites findAllUserId(Integer userId) {

        var it = favoritesRepository.findAll();

        List<Favorites> favorites = new ArrayList<>();
        for (Favorites fav : it) {
            if (fav.getUser_id().equals(userId)) {
                favorites.add(fav);
            }
        }

        if (it.isEmpty()) {
            return new AllFavorites(new ArrayList<>(), "", 200);
        }
        var allFavorites = new AllFavorites(favorites, "", 200);

        return allFavorites;
    }

    public FavoriteExtracted findFavoriteExtract(Integer userId) {

        var it = favoritesRepository.findAll();


        List<Favorites> favorites = new ArrayList<>();

        for (Favorites fav : it) {
            if (fav.getUser_id().equals(userId)) {
                favorites.add(fav);
            }
        }

        if (it.isEmpty()) {
            return new FavoriteExtracted(new ArrayList<>(), "", 200);
        }

        List<FavoriteUtils> favoriteUtilsList = new ArrayList<>();
        for (Favorites fav : favorites) {

            SingleProduct singleProduct = productService.findById(fav.getProduct_id());
            SingleUser singleUser = userService.findById(singleProduct.getProduct().getUser_id());

            FavoriteUtils favoriteUtils = new FavoriteUtils();

            favoriteUtils.setId(fav.getId());
            favoriteUtils.setProduct_id(singleProduct.getProduct().getId());
            favoriteUtils.setBrand(singleProduct.getProduct().getBrand());
            favoriteUtils.setPrice(singleProduct.getProduct().getPrice());
            favoriteUtils.setSeller(singleUser.getUser().getUser_name());

            favoriteUtilsList.add(favoriteUtils);
        }

        FavoriteExtracted extractedList = new FavoriteExtracted(favoriteUtilsList, "", 200);

        return extractedList;
    }

    public FavoriteExtractedProduct findFavoriteExtractProduct(Integer userId, Integer productId) {
        FavoriteExtracted favoriteExtracted = findFavoriteExtract(userId);

        for (FavoriteUtils favoriteUtils : favoriteExtracted.getFavoriteUtilsList()) {
            if (favoriteUtils.getId().equals(productId)) {
                return new FavoriteExtractedProduct(favoriteUtils, "", 200);
            }
        }

        return new FavoriteExtractedProduct(new FavoriteUtils(), "", 200);
    }

    public SingleFavorite findById(Integer id) {
        var result = favoritesRepository.findById(id);
        if (result.isEmpty()) {
            return new SingleFavorite(null, "Favorite Product not found", 404);
        }

        SingleFavorite favorite = new SingleFavorite(result.get(), "", 200);

        return favorite;
    }

    public FavoriteAdded addFav(Integer userId, Integer productId) {

        AllFavorites favProducts = findAll();
        for (Favorites fav : favProducts.getFavorites()) {
            if (fav.getProduct_id().equals(productId) && fav.getUser_id().equals(userId)) {
                return new FavoriteAdded(fav, "Product already exists in Favorites List!", 409);
            }
        }

        boolean validUser = false;
        boolean validProduct = false;

        AllProducts products = productService.findAll();
        for (Product p : products.getProducts()) {
            if (p.getId().equals(productId)) {
                validProduct = true;
                break;
            }
        }

        AllUsers users = userService.findAll();
        for (User u : users.getUsers()) {
            if (u.getId().equals(userId)) {
                validUser = true;
                break;
            }
        }

        if (!validProduct) {
            return new FavoriteAdded(new Favorites(),"You can't add to Favorite a product that is not in the Catalog!",404);
        }

        if (!validUser) {
            return new FavoriteAdded(new Favorites(),"Invalid user id for favorite product",404);
        }

        Favorites favorite = new Favorites();
        favorite.setUser_id(userId);
        favorite.setProduct_id(productId);

        var result = favoritesRepository.save(favorite);

        return new FavoriteAdded(result,"",200);
    }

    public FavoriteDeleted deleteById(Integer userId, Integer favoriteId) {
        AllFavorites allFavorites = findAllUserId(userId);
        for(Favorites fav : allFavorites.getFavorites()) {
            if(fav.getId().equals(favoriteId)) {
                favoritesRepository.deleteById(favoriteId);

                return new FavoriteDeleted(fav.getId(), "", 200);
            }
        }

        return new FavoriteDeleted(allFavorites.getFavorites().get(0).getId(), "Product does not belong to user's favorite list", 200);
    }

    public Long count() {

        return favoritesRepository.count();
    }

}

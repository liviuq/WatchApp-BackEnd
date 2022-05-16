package platform.webapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.webapplication.entities.Product;
import platform.webapplication.models.Products.AllProducts;
import platform.webapplication.models.Products.ProductAdded;
import platform.webapplication.models.Products.ProductUpdated;
import platform.webapplication.models.Products.SingleProduct;
import platform.webapplication.repository.ProductRepository;
import platform.webapplication.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository productRepository;
    private UserRepository  userRepository;


    @Autowired
    public ProductService(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public AllProducts findAll() {
        var it = productRepository.findAll();

        if (it.isEmpty()) {

            return new AllProducts(new ArrayList<Product>(), "", 204);
        }
        var products = new ArrayList<Product>();
        it.forEach(e -> products.add(e));

        return new AllProducts(products, "", 200);
    }

    public SingleProduct findById(Integer id) {

        var result = productRepository.findById(id);

        if (result.isEmpty()) {
            return new SingleProduct(null, "Product not found", 404);
        }
        SingleProduct product = new SingleProduct(result.get(), "", 200);

        return product;
    }

    public Long count() {

        return productRepository.count();
    }

    public void deleteById(Integer productId) {
        productRepository.deleteById(productId);
    }

    public ProductAdded add(Product product, Integer userId) {
        ProductAdded productAdded = new ProductAdded();
        var user = userRepository.findById(userId);

        if(user == null){
            productAdded.setError("User inexistet! Daca doriti sa adaugati un produs, intai trebuie sa va conectati");
            productAdded.setStatusCode(404);
            return productAdded;
        }

        var result = productRepository.save(product);

        if (result == null) {

            productAdded.setError("A aparut o eroare in cadrul salvarii produsului. Va rugam reincercati mai tarziu!");
            productAdded.setStatusCode(500);
            return productAdded;
        }

        productAdded.setProduct(result);
        productAdded.setError("");
        productAdded.setStatusCode(201);
        return productAdded;
    }

    public List<String> findAllCategories() {
        var categories = new ArrayList<String>();
        productRepository.findAll().forEach(x -> {
            categories.add(x.getCategory());
        });

        return categories;
    }

    public ProductUpdated update(Integer id, Product product) {
        ProductUpdated productUpdated = new ProductUpdated();
        Optional<Product> result = productRepository.findById(id);

        if (result.isEmpty()) {
            productUpdated.setError("Product not found");
            productUpdated.setStatusCode(404);

            return productUpdated;
        } else {
            Product entity = result.get();
            Integer identifier = entity.getId();
            entity = product;
            entity.setId(identifier);

            productUpdated.setProduct(productRepository.save(entity));
            productUpdated.setStatusCode(202);

            return productUpdated;
        }
    }

    public ProductUpdated addPromotedProduct(Integer id) {
        ProductUpdated productUpdated = new ProductUpdated();
        Optional<Product> result = productRepository.findById(id);

        if (result.isPresent()) {
            Product entity = result.get();
            entity.setPromovat((byte) 1);

            productUpdated.setProduct(productRepository.save(entity));
            productUpdated.setStatusCode(202);

            return productUpdated;
        } else {
            productUpdated.setError("Product not found");
            productUpdated.setStatusCode(404);

            return productUpdated;
        }
    }

    public ProductUpdated removePromotedProduct(Integer id) {
        ProductUpdated productUpdated = new ProductUpdated();
        Optional<Product> result = productRepository.findById(id);

        if (result.isPresent()) {
            Product entity = result.get();
            entity.setPromovat((byte) 0);

            productUpdated.setProduct(productRepository.save(entity));
            productUpdated.setStatusCode(202);

            return productUpdated;
        } else {
            productUpdated.setError("Product not found");
            productUpdated.setStatusCode(404);

            return productUpdated;
        }
    }

    public Integer getUserId(Integer id) {
        return productRepository.getById(id).getUser_id();
    }

    public boolean checkUserExists(Integer id) {
        return userRepository.findById(id).isPresent();
    }


    public List<Product> listFilteredProducts(String filter) {
        if (filter != null) {
            return productRepository.search(filter);
        }
        return productRepository.findAll();
    }
    //page aici + favorites etc (paginare), raspunsuri paginate

}

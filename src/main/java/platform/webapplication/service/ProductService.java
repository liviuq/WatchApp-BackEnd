package platform.webapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.webapplication.entities.Carcase;
import platform.webapplication.entities.CarcaseColor;
import platform.webapplication.entities.Category;
import platform.webapplication.entities.Product;
import platform.webapplication.models.Products.AllProducts;
import platform.webapplication.models.Products.ProductAdded;
import platform.webapplication.models.Products.ProductUpdated;
import platform.webapplication.models.Products.SingleProduct;
import platform.webapplication.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final UserRepository  userRepository;
    private final BrandRepository brandRepository;
    private final CarcaseRepository carcaseRepository;
    private final CarcaseColorRepository carcaseColorRepository;
    private final CarcaseFormRepository carcaseFormRepository;
    private final CarcaseThicknessRepository carcaseThicknessRepository;
    private final CategoryRepository categoryRepository;
    private final GenderRepository genderRepository;
    private final GlassRepository glassRepository;
    private final MechanismRepository mechanismRepository;
    private final ModelRepository modelRepository;
    private final StrapRepository strapRepository;
    private final StrapColorRepository strapColorRepository;
    private final WaterResistanceRepository waterResistanceRepository;
    private final YearRepository yearRepository;


    @Autowired
    public ProductService(
            ProductRepository productRepository,
            UserRepository userRepository,
            BrandRepository brandRepository,
            CarcaseRepository carcaseRepository,
            CarcaseColorRepository carcaseColorRepository,
            CarcaseFormRepository carcaseFormRepository,
            CarcaseThicknessRepository carcaseThicknessRepository,
            CategoryRepository categoryRepository,
            GenderRepository genderRepository,
            GlassRepository glassRepository,
            MechanismRepository mechanismRepository,
            ModelRepository modelRepository,
            StrapRepository strapRepository,
            StrapColorRepository strapColorRepository,
            WaterResistanceRepository waterResistanceRepository,
            YearRepository yearRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.brandRepository = brandRepository;
        this.carcaseRepository = carcaseRepository;
        this.carcaseColorRepository = carcaseColorRepository;
        this.carcaseFormRepository = carcaseFormRepository;
        this.carcaseThicknessRepository = carcaseThicknessRepository;
        this.categoryRepository = categoryRepository;
        this.genderRepository = genderRepository;
        this.glassRepository = glassRepository;
        this.mechanismRepository = mechanismRepository;
        this.modelRepository = modelRepository;
        this.strapRepository = strapRepository;
        this.strapColorRepository = strapColorRepository;
        this.waterResistanceRepository = waterResistanceRepository;
        this.yearRepository = yearRepository;
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

}

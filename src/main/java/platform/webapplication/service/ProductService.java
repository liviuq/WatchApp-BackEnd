package platform.webapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.webapplication.entities.Conditions;
import platform.webapplication.entities.Product;
import platform.webapplication.models.Products.AllProducts;
import platform.webapplication.models.Products.ProductAdded;
import platform.webapplication.models.Products.ProductUpdated;
import platform.webapplication.models.Products.SingleProduct;
import platform.webapplication.repository.*;
import platform.webapplication.models.Products.*;
import platform.webapplication.repository.ProductRepository;
import platform.webapplication.repository.UserRepository;

import java.util.ArrayList;
import java.util.HashSet;
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
    private final ConditionsRepository conditionsRepository;
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
            ConditionsRepository conditionsRepository,
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
        this.conditionsRepository = conditionsRepository;
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

        if (user == null) {
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
            entity.setPromoted((byte) 1);

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
            entity.setPromoted((byte) 0);

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
  
    public ProductFilters getFilters() {
        ProductFilters productFilters = new ProductFilters();
        var brands = brandRepository.findAll();
        if(!brands.isEmpty()){
            brands.forEach(x-> productFilters.addBrand(x.getBrand()));
        }

        var carcseColors = carcaseColorRepository.findAll();
        if(!carcseColors.isEmpty()){
            carcseColors.forEach(x -> productFilters.addCarcaseColor(x.getCarcase_color()));
        }

        var carcaseForms = carcaseFormRepository.findAll();
        if(!carcaseForms.isEmpty()){
            carcaseForms.forEach(x -> productFilters.addCarcaseForm(x.getCarcase_form()));
        }

        var carcaseThicknesses = carcaseThicknessRepository.findAll();
        if(!carcaseThicknesses.isEmpty()){
            carcaseThicknesses.forEach(x -> productFilters.addCarcaseTickness(x.getCarcase_thickness()));
        }

        var carcases = carcaseRepository.findAll();
        if(!carcases.isEmpty()){
            carcases.forEach(x -> productFilters.addCarcase(x.getCarcase()));
        }

        var categories = categoryRepository.findAll();
        if(!categories.isEmpty()){
            categories.forEach(x -> productFilters.addCategory(x.getCategory()));
        }

        var genders = genderRepository.findAll();
        if(!genders.isEmpty()){
            genders.forEach(x -> productFilters.addGender(x.getGender()));
        }

        var glasses = glassRepository.findAll();
        if(!glasses.isEmpty()){
            glasses.forEach(x -> productFilters.addGlass(x.getGlass()));
        }

        var mechanisms = mechanismRepository.findAll();
        if(!mechanisms.isEmpty()){
            mechanisms.forEach(x -> productFilters.addMechanism(x.getMechanism()));
        }

        var models = modelRepository.findAll();
        if(!models.isEmpty()){
            models.forEach(x -> productFilters.addModel(x.getModel()));
        }

        var strapColors = strapColorRepository.findAll();
        if(!strapColors.isEmpty()){
            strapColors.forEach(x -> productFilters.addStrapColors(x.getStrap_color()));
        }

        var straps = strapRepository.findAll();
        if(!straps.isEmpty()){
            straps.forEach(x -> productFilters.addStrap(x.getStrap()));
        }

        var waterResistance = waterResistanceRepository.findAll();
        if(!waterResistance.isEmpty()){
            waterResistance.forEach(x -> productFilters.addWaterResistence(x.getWater_resistance()));
        }

        var years = yearRepository.findAll();
        if(!years.isEmpty()){
            years.forEach(x -> productFilters.addYears(x.getYear()));
        }

        var conditions = conditionsRepository.findAll();
        if(!conditions.isEmpty()){
            conditions.forEach(x -> productFilters.addCondition(x.getConditions()));
        }
        productFilters.setError("");
        productFilters.setStatusCode(200);

        return productFilters;
    }

    // page aici + favorites etc (paginare), raspunsuri paginate
    // import org.springframework.data.domain.Page;
    public HashSet<Product> listSearchedProducts(String brand, String category, String year, String strap, String strapColor) {
        HashSet<Product> products = new HashSet<>();

        if (brand != null || category != null || year != null || strap != null || strapColor != null) {
            products.addAll(productRepository.search(brand, category, year, strap, strapColor));

            return products;
        }
        products.addAll(productRepository.findAll());
        return products;
    }

}

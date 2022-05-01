package platform.webapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.webapplication.enitites.Product;
import platform.webapplication.models.AllProducts;
import platform.webapplication.models.ProductAdded;
import platform.webapplication.models.ProductUpdated;
import platform.webapplication.models.SingleProduct;
import platform.webapplication.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository productRepository;


    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public AllProducts findAll() {
        var it = productRepository.findAll();

        if(it.isEmpty())
        {

            return new AllProducts(new ArrayList<Product>(), "", 200);
        }
        var products = new ArrayList<Product>();
        it.forEach(e -> products.add(e));

        return new AllProducts(products, "", 200);
    }

    public SingleProduct findById(Integer id) {

        var result = productRepository.findById(id);

        if(result.isEmpty()){
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

    public ProductAdded add(Product product) {
        ProductAdded productAdded = new ProductAdded();
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
}

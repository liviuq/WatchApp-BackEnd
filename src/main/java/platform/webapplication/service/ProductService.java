package platform.webapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.webapplication.enitites.Product;
import platform.webapplication.models.ProductAdded;
import platform.webapplication.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;


    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {

        var it = productRepository.findAll();

        var products = new ArrayList<Product>();
        it.forEach(e -> products.add(e));

        return products;
    }

    public Product findById(Integer id) {

        var product = productRepository.findById(id);
        return product.orElse(null);
    }

    public Long count() {

        return productRepository.count();
    }

    public void deleteById(Integer productId) {
        productRepository.deleteById(productId);
    }

    public ProductAdded add(Product product)
    {
        ProductAdded productAdded = new ProductAdded();

        var result = productRepository.save(product);
        productAdded.setProduct(result);

        if(result == null){

            productAdded.setError("A aparut o eroare in cadrul salvarii produsului. Va rugam reincercati mai tarziu!");
            productAdded.setStatusCode(500);
            return productAdded;
        }

        productAdded.setError("");
        productAdded.setStatusCode(201);
        return productAdded;
    }

    public List<String> findAllCategories()
    {
        var categories = new ArrayList<String>();
        productRepository.findAll().forEach(x -> {
            categories.add(x.getCategory());
        });

        return categories;
    }
}

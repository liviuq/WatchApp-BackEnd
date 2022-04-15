package platform.webapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.webapplication.model.Product;
import platform.webapplication.model.User;
import platform.webapplication.repository.ProductRepository;
import platform.webapplication.repository.UserRepository;

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
}

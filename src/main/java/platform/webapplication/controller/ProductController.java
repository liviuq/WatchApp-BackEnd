package platform.webapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import platform.webapplication.entities.Product;
import platform.webapplication.entities.User;
import platform.webapplication.models.ProductAdded;
import platform.webapplication.service.ProductService;

import java.util.List;

@RestController
@RequestMapping(path="/product")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public List<Product> all() {
        return productService.findAll();
    }

    @PostMapping()
    public ProductAdded add(@RequestBody Product product, @RequestHeader Integer id) {
       var result = productService.add(product);
       return result;
    }

    @GetMapping("/product-categories")
    public List<String> categories() {
        return productService.findAllCategories();
    }
}

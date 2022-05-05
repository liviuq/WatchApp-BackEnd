package platform.webapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import platform.webapplication.entities.Product;
import platform.webapplication.models.Products.AllProducts;
import platform.webapplication.models.Products.ProductAdded;
import platform.webapplication.models.Products.ProductUpdated;
import platform.webapplication.models.Products.SingleProduct;
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
    public AllProducts all() {
        AllProducts result = productService.findAll();
        return result;
    }

    @GetMapping("{id}")
    public SingleProduct byId(@PathVariable Integer id){
        SingleProduct result = productService.findById(id);
        return result;
    }

    @GetMapping("/product-categories")
    public List<String> categories() {
        return productService.findAllCategories();
    }

    @PostMapping("{userId}")
    public ProductAdded add(@PathVariable Integer userId, @RequestBody Product product) {
        product.setUser_id(userId);
        ProductAdded result = productService.add(product, userId);
       return result;
    }

    @PutMapping("{id}")
    public ProductUpdated update(@PathVariable Integer id, @RequestBody Product product) {
        ProductUpdated result = productService.update(id, product);
        return result;
    }
}

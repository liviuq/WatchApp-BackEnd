package platform.webapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import platform.webapplication.model.Product;
import platform.webapplication.service.ProductService;

import java.util.List;

@RestController
@RequestMapping(path="/homepage")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public List<Product> allproduct() {
        return productService.findAll();
    }

    @GetMapping("product")
    public List<Product> promotedProducts() {
        return productService.findPromoted();
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Integer id) {
        productService.deleteById(id);
    }
}

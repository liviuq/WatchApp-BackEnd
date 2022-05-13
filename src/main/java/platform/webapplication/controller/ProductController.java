package platform.webapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import platform.webapplication.entities.Product;
import platform.webapplication.models.Products.*;
import platform.webapplication.service.ProductService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
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
    public ProductAdded add(@PathVariable Integer userId, @Valid @RequestBody Product product) {
        product.setUser_id(userId);
        ProductAdded result = productService.add(product, userId);
       return result;
    }

    @PutMapping("{id}")
    public ProductUpdated update(@PathVariable Integer id, @Valid @RequestBody Product product) {
        ProductUpdated result = productService.update(id, product);
        return result;
    }

    @DeleteMapping("{user_id}/delete/{id}")
    public ProductDeleted deleteUserProduct(@PathVariable Integer user_id, @PathVariable Integer id){
        //check to see if product id exists
        for(Product product : productService.findAll().getProducts())
        {
            //product with this id exists.
            if(product.getId().equals(id))
            {
                //check to see if this product belongs to user_id
                if(productService.getUserId(id).equals(user_id))
                {
                    productService.deleteById(id);
                    return new ProductDeleted(id, "", 200);
                }
                else
                {
                    return new ProductDeleted(id, "Product with this id does not belong to user", 200);
                }
            }
        }

        //product not found
        return new ProductDeleted(id, "This product id does not exist in the database", 200);
    }

    //list your own products
    @GetMapping("{user_id}/products")
    public AllProducts getUserProducts(@PathVariable Integer user_id) {
        //check to see if user_id exists
        if(productService.checkUserExists(user_id))
        {
            //creating the array of products that belong to user_id
            List<Product> products = new ArrayList<>();
            for(Product product : productService.findAll().getProducts())
            {
                //verify that product belongs to user_id
                if(product.getUser_id().equals(user_id))
                {
                    //add product to the array
                    products.add(product);
                }
            }
            return new AllProducts(products, "", 200);
        }

        return new AllProducts(new ArrayList<>(), "User has no products", 200);
    }
}

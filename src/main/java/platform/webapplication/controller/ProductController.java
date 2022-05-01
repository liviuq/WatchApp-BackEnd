package platform.webapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import platform.webapplication.model.Product;
import platform.webapplication.service.ProductService;

import java.util.ArrayList;
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
    //delete for admins only
    public void delete(@PathVariable Integer id) {
        productService.deleteById(id);
    }

    @DeleteMapping("{user_id}/delete/{id}")
    public void deleteUserProduct(@PathVariable Integer user_id, @PathVariable Integer id){
        //check to see if product with this id belongs to user_id
        //edit1: endpoint will be caclled by the frontend team
        //so no verification is needed

        //edit2: verification is done!
        if(productService.getUserId(id).equals(user_id))
        {
            productService.deleteById(id);
        }
        else
        {
            //edit for production's sake
            System.out.println("WA NEBUNULE");
        }
    }

    //list your own products
    @GetMapping("{user_id}/products")
    public List<Product> getUserProducts(@PathVariable Integer user_id) {
        //check to see if user_id exists
        if(productService.checkUserExists(user_id))
        {
            //creating the array of products that belong to user_id
            List<Product> products = new ArrayList<>();
            for(Product product : productService.findAll())
            {
                //verify that product belongs to user_id
                if(product.getUser_id().equals(user_id))
                {
                    //add product to the array
                    products.add(product);
                }
            }
            return products;
        }
        return new ArrayList<>();
    }
}

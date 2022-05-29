package platform.webapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import platform.webapplication.entities.Product;
import platform.webapplication.models.Products.*;
import platform.webapplication.service.ProductService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*", exposedHeaders = "Access-Control-Allow-Origin")
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

    @GetMapping("filters")
    public ProductFilters GetFilters(){
        return productService.getFilters();
    }

    // http://localhost:5000/product/search?brand=&category=clasic&price=59.99
    @GetMapping("search")
    public HashSet<Product> searchedProducts(@RequestParam(value = "brand", required = false) String brand, @RequestParam(value = "category", required = false) String category, @RequestParam(value = "year", required = false) String year, @RequestParam(value = "strap", required = false) String strap, @RequestParam(value = "color", required = false) String color) {
        HashSet<Product> productList = productService.listSearchedProducts(brand, category, year, strap, color);

        return productList;
    }

    @GetMapping("search/{pageNo}")
    public AllProducts searchedProductsPaged(@PathVariable Integer pageNo, @RequestParam(value = "brand", required = false) String brand, @RequestParam(value = "category", required = false) String category, @RequestParam(value = "year", required = false) String year, @RequestParam(value = "strap", required = false) String strap, @RequestParam(value = "color", required = false) String color) {
        HashSet<Product> productList = productService.listSearchedProductsPaged(pageNo, brand, category, year, strap, color);
        List<Product> arrayListProducts = new ArrayList<>(productList);

        Pageable paging = PageRequest.of(pageNo, 2);
        Page<Product> page = new PageImpl<>(arrayListProducts, paging, arrayListProducts.size());
        if(page.hasContent())
        {
            return new AllProducts(page.getContent(), "", 200);
        }
        else
        {
            return new AllProducts(new ArrayList<>(), "No products", 404);
        }
    }

    @GetMapping("/promoted/{pageNo}")
    public AllProducts listPromoted(@PathVariable Integer pageNo)
    {
        AllProducts result = productService.findAllPaginated(pageNo);
        return result;
    }
}

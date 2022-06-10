package platform.webapplication.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.parameters.P;
import platform.webapplication.entities.Product;
import platform.webapplication.entities.User;
import platform.webapplication.models.Products.ProductAdded;
import platform.webapplication.repository.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @MockBean
    private ProductRepository repo;
    @MockBean
    private  ProductRepository productRepository;
    @MockBean
    private  UserRepository userRepository;
    @MockBean
    private  BrandRepository brandRepository;
    @MockBean
    private  CarcaseRepository carcaseRepository;
    @MockBean
    private  CarcaseColorRepository carcaseColorRepository;
    @MockBean
    private  CarcaseFormRepository carcaseFormRepository;
    @MockBean
    private  CarcaseThicknessRepository carcaseThicknessRepository;
    @MockBean
    private  CategoryRepository categoryRepository;
    @MockBean
    private  GenderRepository genderRepository;
    @MockBean
    private  GlassRepository glassRepository;
    @MockBean
    private  MechanismRepository mechanismRepository;
    @MockBean
    private  ModelRepository modelRepository;
    @MockBean
    private  StrapRepository strapRepository;
    @MockBean
    private  StrapColorRepository strapColorRepository;
    @MockBean
    private  ConditionsRepository conditionsRepository;
    @MockBean
    private  WaterResistanceRepository waterResistanceRepository;
    @MockBean
    private  YearRepository yearRepository;
    private List<Product> products;
    private ProductService productService;
    private static Product product;
    private static User user;

    @BeforeEach
    void setUp() {
        products = new ArrayList<>();
        product = new Product("Casio", 100f);
        user = new User();

        List<Product> searchedProducts = new ArrayList<>();
        searchedProducts.add(new Product("Casio", 100.00f));
        searchedProducts.add(new Product("Q&A", 125.00f));

        this.productRepository = mock(ProductRepository.class);
        when(productRepository.findAll()).thenReturn(products);
        when(productRepository.findById(anyInt())).thenReturn(Optional.ofNullable(product));
        doNothing().when(productRepository).deleteById(anyInt());
        when(productRepository.save(product)).thenReturn(product);
        when(productRepository.getById(anyInt())).thenReturn(new Product(5));
        when(productRepository.search(anyString(), anyString(), anyString(), anyString(), anyString())).thenReturn(searchedProducts);


        this.userRepository = mock(UserRepository.class);
        when(userRepository.findById(anyInt())).thenReturn(Optional.ofNullable(user));
        this.brandRepository = mock(BrandRepository.class);
        this.carcaseRepository = mock(CarcaseRepository.class);
        this.carcaseColorRepository = mock(CarcaseColorRepository.class);
        this.carcaseFormRepository = mock(CarcaseFormRepository.class);
        this.carcaseThicknessRepository = mock(CarcaseThicknessRepository.class);
        this.categoryRepository = mock(CategoryRepository.class);
        this.genderRepository = mock(GenderRepository.class);
        this.glassRepository = mock(GlassRepository.class);
        this.mechanismRepository = mock(MechanismRepository.class);
        this.modelRepository = mock(ModelRepository.class);
        this.strapRepository = mock(StrapRepository.class);
        this.strapColorRepository = mock(StrapColorRepository.class);
        this.conditionsRepository = mock(ConditionsRepository.class);
        this.waterResistanceRepository = mock(WaterResistanceRepository.class);
        this.yearRepository = mock(YearRepository.class);

        productService = new ProductService(
                productRepository,
                userRepository,
                brandRepository,
                carcaseRepository,
                carcaseColorRepository,
                carcaseFormRepository,
                carcaseThicknessRepository,
                categoryRepository,
                genderRepository,
                glassRepository,
                mechanismRepository,
                modelRepository,
                strapRepository,
                strapColorRepository,
                conditionsRepository,
                waterResistanceRepository,
                yearRepository);


    }
    @Test
    void findAll() {
        //Arrange
        products.add(new Product("Casio", 100.00f));
        products.add(new Product("Q&A", 125.00f));
        products.add(new Product("Armani", 500.00f));
        products.add(new Product("Rolex", 1000.00f));

        //Act
        var actual = productService.findAll();

        //Assert
        assertEquals(200, actual.getStatusCode());
        assertEquals("",actual.getError());
        assertEquals(products,actual.getProducts());
    }

    @Test
    void findById(){
        //Act
        var actual = productService.findById(anyInt());

        //Assert
        assertEquals(200, actual.getStatusCode());
        assertEquals("",actual.getError());
        assertEquals(product, actual.getProduct());
    }

    @Test
    void deleteById() {
        //Act
        productService.deleteById(anyInt());
        //Assert
        verify(productRepository, times(1)).deleteById(anyInt());
    }

    @Test
    void add() {
        //Arrange
        ProductAdded productAdded = new ProductAdded();
        productAdded.setProduct(product);
        productAdded.setError("");
        productAdded.setStatusCode(201);

        //Act
        var actual = productService.add(product, anyInt());

        //Assert
        assertEquals(201, actual.getStatusCode());
        assertEquals("",actual.getError());
        assertEquals(product, actual.getProduct());
    }

    @Test
    void findAllCategories() {
        //Arrange
        List<String> categories = new ArrayList<>();
        categories.add("Ceasuri de lux");
        categories.add("Ceasuri cronograf");
        categories.add("Ceasuri de aur");
        categories.add("Ceasuri vintage");
        products.add(new Product("Casio", "Ceasuri de lux"));
        products.add(new Product("Q&A", "Ceasuri cronograf"));
        products.add(new Product("Armani", "Ceasuri de aur"));
        products.add(new Product("Rolex", "Ceasuri vintage"));

        //Act
        var actual = productService.findAllCategories();

        //Assert
        assertNotNull(actual);
        assertEquals(categories, actual);
    }

    @Test
    void update() {
        //Act
        var actual = productService.update(anyInt(), product);

        //Assert
        assertEquals(202, actual.getStatusCode());
        assertEquals("", actual.getError());
        assertEquals(product, actual.getProduct());
    }

    @Test
    void getUserId() {
        //Act
        var actual = productService.getUserId(anyInt());

        //Assert
        assertEquals(5, actual);
    }

    @Test
    void checkUserExists() {
        //Act
        var actual = productService.checkUserExists(anyInt());

        //Assert
        assertTrue(actual);
    }

    @Test
    void listSearchedProductsWhenSearchElementsAreNull() {
        //Arrange
        products.add(new Product("Casio", 100.00f));
        products.add(new Product("Q&A", 125.00f));
        products.add(new Product("Armani", 500.00f));
        products.add(new Product("Rolex", 1000.00f));

        //Act
        var actual = productService.listSearchedProducts(null, null, null, null, null);

        //Assert
        assertEquals(4, actual.size());

    }

    @Test
    void listSearchedProductsWhenSearchElementsAreNotNull() {
        //Arrange
        Set<Product> productSet = new HashSet<>();
        productSet.add(new Product("Casio", 100.00f));
        productSet.add(new Product("Q&A", 125.00f));
        productSet.add(new Product("Armani", 500.00f));
        productSet.add(new Product("Rolex", 1000.00f));

        //Act
        var actual = productService.listSearchedProducts(anyString(), anyString(), anyString(), anyString(), anyString());

        //Assert
        assertEquals(2, actual.size());

    }
}
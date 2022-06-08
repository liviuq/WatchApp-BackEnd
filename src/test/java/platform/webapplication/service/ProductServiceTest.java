package platform.webapplication.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import platform.webapplication.entities.Product;
import platform.webapplication.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    @BeforeEach
    void setUp() {
        products = new ArrayList<>();
        product = new Product("Casio", 100f);

        this.productRepository = mock(ProductRepository.class);
        when(productRepository.findAll()).thenReturn(products);
        when(productRepository.findById(anyInt())).thenReturn(Optional.ofNullable(product));

        this.userRepository = mock(UserRepository.class);
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
    void findAllPaginated() {

    }

    @Test
    void deleteById() {
    }

    @Test
    void add() {
    }

    @Test
    void findAllCategories() {
    }

    @Test
    void update() {
    }

    @Test
    void findAllPromoted() {
    }

    @Test
    void addPromotedProduct() {
    }

    @Test
    void removePromotedProduct() {
    }

    @Test
    void getUserId() {
    }

    @Test
    void checkUserExists() {
    }

    @Test
    void getFilters() {
    }

    @Test
    void listSearchedProducts() {
    }



    @Test
    void listSearchedProductsPaged() {
    }
}
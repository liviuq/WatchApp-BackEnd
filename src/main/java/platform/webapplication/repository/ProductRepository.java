package platform.webapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import platform.webapplication.entities.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT p FROM Product p WHERE (trim(lower(p.brand)) = :brand OR :brand = '')"
            + " AND (CONCAT(p.price, '') =  CONCAT(:price, '') OR CONCAT(:price, '') = '')"
            + " AND (trim(lower(p.category)) =  :category OR :category = '')"
            + " AND (CONCAT(p.year, '') =  CONCAT(:price, '') OR CONCAT(:year, '') = '')")

    List<Product> search(@Param("brand") String brand, @Param("price") String price, @Param("category") String category, @Param("year") String year);
}

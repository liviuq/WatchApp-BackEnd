package platform.webapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import platform.webapplication.entities.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT p FROM Product p WHERE (trim(lower(p.brand)) = :brand OR :brand = '' OR :brand is null)"
            + " AND (trim(lower(p.category)) = :category OR :category = '' OR :category is null)"
            + " AND (trim(lower(p.strap)) = :strap OR :strap = '' OR :strap is null)"
            + " AND (trim(lower(p.strap_color)) = :color OR :color = '' OR :color is null)"
            + " AND (CONCAT(p.year, '') =  CONCAT(:year, '') OR CONCAT(:year, '') = '' OR CONCAT(:year, '') is null)")

    List<Product> search(@Param("brand") String brand, @Param("category") String category, @Param("year") String year, @Param("strap") String strap, @Param("color") String strapColor);
}

package platform.webapplication.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import platform.webapplication.entities.Product;

import java.util.Collection;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT p FROM Product p WHERE (trim(lower(p.brand)) = :brand OR :brand = '' OR :brand is null)"
            + " AND (trim(lower(p.category)) = :category OR :category = '' OR :category is null)"
            + " AND (trim(lower(p.strap)) = :strap OR :strap = '' OR :strap is null)"
            + " AND (trim(lower(p.strap_color)) = :color OR :color = '' OR :color is null)"
            + " AND (CONCAT(p.year, '') =  CONCAT(:year, '') OR CONCAT(:year, '') = '' OR CONCAT(:year, '') is null)"
    )
    List<Product> search(@Param("brand") String brand, @Param("category") String category, @Param("year") String year, @Param("strap") String strap, @Param("color") String strapColor);


    @Query("SELECT p FROM Product p WHERE (trim(lower(p.brand)) = :brand OR :brand = '' OR :brand is null)"
            + " AND (trim(lower(p.strap)) = :strap OR :strap = '' OR :strap is null)"
            + " AND (trim(lower(p.strap_color)) = :color OR :color = '' OR :color is null)"
            + " AND (CONCAT(p.year, '') =  CONCAT(:year, '') OR CONCAT(:year, '') = '' OR CONCAT(:year, '') is null)"
            + " AND (trim(lower(p.mechanism)) = :mechanism OR :mechanism = '' OR :mechanism is null)"
            + " AND (trim(lower(p.carcase_form)) = :form OR :form = '' OR :form is null)"
            + " AND (trim(lower(p.carcase_color)) = :carcaseColor OR :carcaseColor = '' OR :carcaseColor is null)"
            + " AND (trim(lower(p.conditions)) = :condition OR :condition = '' OR :condition is null)"
            + " AND (trim(lower(p.carcase_thickness)) = :thickness OR :thickness = '' OR :thickness is null)"
            + " AND (trim(lower(p.carcase)) = :carcase OR :carcase= '' OR :carcase is null)"
            + " AND (CONCAT(p.gender, '') =  CONCAT(:gender, '') OR CONCAT(:gender, '') = '' OR CONCAT(:gender, '') is null)"
            + " AND (CONCAT(p.alarm, '') =  CONCAT(:alarm, '') OR CONCAT(:alarm, '') = '' OR CONCAT(:alarm, '') is null)"
            + " AND (CONCAT(p.timer, '') =  CONCAT(:timer, '') OR CONCAT(:timer, '') = '' OR CONCAT(:timer, '') is null)"
            + " AND (CONCAT(p.water_resistance, '') =  CONCAT(:waterResistance, '') OR CONCAT(:waterResistance, '') = '' OR CONCAT(:waterResistance, '') is null)"
    )
    List<Product> searchFull(@Param("gender") String gender,@Param("brand") String brand, @Param("mechanism")String mechanism, @Param("strap") String strap, @Param("form") String form, @Param("carcaseColor") String carcaseColor, @Param("condition")String condition, @Param("color")String strapColor, @Param("thickness")String thickness, @Param("alarm") String alarm, @Param("year")String year,@Param("carcase") String carcase, @Param("waterResistance")String waterResistance,@Param("timer") String timer);
}

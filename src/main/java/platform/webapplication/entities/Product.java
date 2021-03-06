package platform.webapplication.entities;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer user_id;
    @NotNull(message = "Brand should not be null!")
    private String brand;
    @NotNull(message = "Price should not be null!")
    private Float price;
    private Date date;
    @NotNull(message = "Year should not be null!")
    private Integer year;
    @NotNull(message = "Strap should not be null!")
    private String strap;
    private String glass;
    @NotNull(message = "Strap color should not be null!")
    private String strap_color;
    private String water_resistance;
    private String carcase;
    private String carcase_form;
    private String carcase_color;
    private String carcase_thickness;
    private Byte alarm;
    private Byte timer;
    @NotNull(message = "Mechanism should not be null!")
    private String mechanism;
    private Integer rating;
    @NotNull(message = "Gender should not be null!")
    private Byte gender;
    private Byte promoted;
    private String category;
    @NotNull(message = "Model should not be null!")
    private String model;
    @NotNull(message = "Condition should not be null!")
    private String conditions;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Product product = (Product) o;
        return id != null && Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

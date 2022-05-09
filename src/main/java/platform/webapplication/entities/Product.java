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
    @NotNull(message = "User id should not be null!")
    private Integer user_id;
    @NotNull(message = "Name should not be null!")
    private String name;
    @NotNull(message = "Price should not be null!")
    private Float price;
    private Date date;
    private Integer stock;
    @NotNull(message = "Year should not be null!")
    private Integer year;
    @NotNull(message = "Strap should not be null!")
    private Integer strap;
    private Integer glass;
    @NotNull(message = "Strap color should not be null!")
    private Integer strap_color;
    private Integer water_resistence;
    private Integer carcase;
    private Integer carcase_form;
    private Integer carcase_color;
    private Integer carcase_thickness;
    private Byte alarm;
    private Byte timer;
    @NotNull(message = "Mechanism should not be null!")
    private Integer mechanism;
    private Integer rating;
    @NotNull(message = "Gender should not be null!")
    private Byte gen;
    @NotNull(message = "Condition should not be null!")
    private String condition;
    private Byte promovat;
    private String category;
    @NotNull(message = "Brand should not be null!")
    private Integer brand;
    @NotNull(message = "Model should not be null!")
    private Integer model;

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

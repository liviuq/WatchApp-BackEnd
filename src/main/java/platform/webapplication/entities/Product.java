package platform.webapplication.entities;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
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
    private String name;
    private Float price;
    private Date date;
    private Integer stock;
    private Integer year;
    private String strap;
    private String glass;
    private String strapColor;
    private Integer water_resistence;
    private String carcase;
    private String carcase_form;
    private String carcase_color;
    private Integer carcase_thickness;
    private Byte alarm;
    private Byte time;
    private String mechanism;
    private Integer rating;
    private String gen;
    private String category;

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

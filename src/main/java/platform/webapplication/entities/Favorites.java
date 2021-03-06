package platform.webapplication.entities;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Favorites {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "Product id should not be null!")
    private Integer product_id;
    @NotNull(message = "User id should not be null!")
    private Integer user_id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Favorites favorites = (Favorites) o;
        return id != null && Objects.equals(id, favorites.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

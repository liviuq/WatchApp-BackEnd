package platform.webapplication.entities;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "Username should not be null!")
    private String user_name;
    @NotNull(message = "Password should not be null!")
    private String user_password;
    @NotNull(message = "First name should not be null!")
    private String first_name;
    @NotNull(message = "Second name should not be null!")
    private String last_name;
    @NotNull(message = "Mail should not be null!")
    private String mail;
    @NotNull(message = "Birthdate should not be null!")
    private Date birth_date;
    private String city;
    @NotNull(message = "Country should not be null!")
    private String county;
    @NotNull(message = "Address should not be null!")
    private String address;
    private String postal_code;
    @NotNull(message = "Phone number should not be null!")
    private String phone_number;
    @NotNull(message = "Cart id should not be null!")
    private Integer cart_id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
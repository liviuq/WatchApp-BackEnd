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
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "Transmitter id should not be null!")
    private Integer from_user;
    @NotNull(message = "Receiver id should not be null!")
    private Integer to_user;
    @NotNull(message = "Date should not be null!")
    private Date time;
    @NotNull(message = "Message cannot be empty!")
    private String  text;

    public Message(Integer from_user, Integer to_user, String text) {
        this.from_user = from_user;
        this.to_user = to_user;
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Message message = (Message) o;
        return id != null && Objects.equals(id, message.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

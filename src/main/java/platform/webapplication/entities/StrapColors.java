package platform.webapplication.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class StrapColors {
    @Id
    private Integer id;
    private String strap_color;
}

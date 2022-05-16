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
public class CarcaseColor {
    @Id
    private Integer id;
    private String carcase_color;
}

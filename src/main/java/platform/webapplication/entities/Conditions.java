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
public class Conditions {
    @Id
    private Integer Id;
    private String conditions;
}

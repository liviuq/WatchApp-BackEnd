package platform.webapplication.models.Users;

import lombok.*;
import platform.webapplication.entities.User;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class SingleUser {
    private User user = new User();
    private String error = "";
    private int statusCode = 500;

}

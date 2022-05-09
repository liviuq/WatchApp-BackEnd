package platform.webapplication.models.Users;

import lombok.*;
import platform.webapplication.entities.User;

import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class SingleUser {
    private User user = new User();
    private String error = "";
    private int statusCode = 500;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SingleUser that = (SingleUser) o;
        return statusCode == that.statusCode && Objects.equals(user, that.user) && Objects.equals(error, that.error);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, error, statusCode);
    }
}

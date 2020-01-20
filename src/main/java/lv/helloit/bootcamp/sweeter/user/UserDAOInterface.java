package lv.helloit.bootcamp.sweeter.user;

import java.util.Map;
import java.util.Optional;

public interface UserDAOInterface {
    void save(User user);

    Map<String, User> getUsers();

    Optional<User> getUserByEmail(String email);

    User getUserById(String userId);
}

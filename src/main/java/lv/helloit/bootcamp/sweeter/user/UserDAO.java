package lv.helloit.bootcamp.sweeter.user;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserDAO {
    private Map<String, User> users;

    public UserDAO() {
        this.users = new HashMap<>();
    }

    public void save(User user) {
        this.users.put(user.getId(), user);
    }

    public Map<String, User> getUsers() {
        return this.users;
    }

    public User getUserByEmail(String email) {
        return this.users.values().stream().filter(user -> user.getEmail().equals(email)).findFirst().orElse(null);
    }

    public User getUserById(String userId) {
        return this.users.get(userId);
    }
}

package lv.helloit.bootcamp.sweeter.user;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserDAO implements UserDAOInterface {
    private Map<String, User> users;

    public UserDAO() {
        this.users = new HashMap<>();
    }

    @Override
    public void save(User user) {
        this.users.put(user.getId(), user);
    }

    @Override
    public Map<String, User> getUsers() {
        return this.users;
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return this.users
                .values()
                .stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst();
    }

    @Override
    public User getUserById(String userId) {
        return this.users.get(userId);
    }
}

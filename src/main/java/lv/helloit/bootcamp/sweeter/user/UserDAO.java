package lv.helloit.bootcamp.sweeter.user;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

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
}

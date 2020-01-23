package lv.helloit.bootcamp.sweeter.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public interface UserDao extends CrudRepository<User, String> {
    public Optional<User> findByEmail(String email);
}

package lv.helloit.bootcamp.sweeter.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
public class UserSqlDao implements UserDAOInterface {
    private final JdbcTemplate jdbcTemplate;

    public UserSqlDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(User user) {
        jdbcTemplate.update("INSERT INTO \"USER\" (id, email, passwordhash) " +
                "VALUES ('" + user.getId() + "', '" + user.getEmail() + "', '" + user.getPasswordHash() + "') ");
    }

    @Override
    public Map<String, User> getUsers() {
        return null;
    }

    @Override
    public Optional<User> getUserByEmail(String searchedEmail) {
        return jdbcTemplate.queryForObject("SELECT * FROM \"USER\" WHERE email = '" + searchedEmail + "'",
                (resultSet, rowNum) -> {
                    User user = new User();
                    user.setId(resultSet.getString("id"));
                    user.setEmail(resultSet.getString("email"));
                    user.setPasswordHash(resultSet.getString("passwordhash"));
                    return Optional.ofNullable(user);
                });
    }

    @Override
    public User getUserById(String searchedUserId) {
        return jdbcTemplate.queryForObject("SELECT * FROM \"USER\" WHERE id = '" + searchedUserId + "'",
                (resultSet, rowNum) -> {
                    User user = new User();
                    user.setId(resultSet.getString("id"));
                    user.setEmail(resultSet.getString("email"));
                    user.setPasswordHash(resultSet.getString("passwordhash"));
                    return user;
                });
    }
}

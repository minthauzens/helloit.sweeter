package lv.helloit.bootcamp.sweeter.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Repository
public class UserSqlDao implements UserDAOInterface {
    public static final String DB_URL = "jdbc:postgresql://localhost:5432/sweetDB";
    public static final String DB_USERNAME = "sweetDBUser";
    public static final String DB_PASSWORD = "U14C4%!%j^KUH^ldZ0d68fv@eusmy*8R";

    public static void main(String[] args) {
    }

    @Override
    public void save(User user) {
        Statement statement = null;
        try {
            statement = getStatement();
            statement.executeUpdate("" +
                    "INSERT INTO \"USER\" (id, email, passwordhash) " +
                    "VALUES ('" + user.getId() + "', '" + user.getEmail() + "', '" + user.getPasswordHash() + "') ");

        } catch (SQLException e) {
            log.error("Error fetching user from DB ", e);
        } finally {
            closeConnection(statement);
        }

    }

    @Override
    public Map<String, User> getUsers() {
        return null;
    }

    @Override
    public Optional<User> getUserByEmail(String searchedEmail) {
        String query = "SELECT * FROM \"USER\" WHERE email = '" + searchedEmail + "'";
        User user = executeQuery(query);
        return Optional.ofNullable(user);
    }

    @Override
    public User getUserById(String searchedUserId) {
        String query = "SELECT * FROM \"USER\" WHERE id = '" + searchedUserId + "'";
        return executeQuery(query);
    }

    private User executeQuery(String query) {
        Statement statement = null;
        try {
            statement = getStatement();
            ResultSet rs = statement.executeQuery(query);
            User user = null;
            if (rs.next()) {
                user = new User();
                user.setId(rs.getString("id"));
                user.setEmail(rs.getString("email"));
                user.setPasswordHash(rs.getString("passwordhash"));
            }

            rs.close();

            return user;
        } catch (SQLException e) {
            log.error("Error fetching user from DB ", e);
            return null;
        } finally {
            closeConnection(statement);
        }
    }

    private Statement getStatement() throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        return connection.createStatement();
    }

    private void closeConnection(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
                if (statement.getConnection() != null) {
                    statement.getConnection().close();
                }
            }
        } catch (SQLException e) {
            System.out.println("Connection already closed!");
        }
    }
}

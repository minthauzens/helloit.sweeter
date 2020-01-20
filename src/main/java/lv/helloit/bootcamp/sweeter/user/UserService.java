package lv.helloit.bootcamp.sweeter.user;

import com.sparkpost.exception.SparkPostException;
import lv.helloit.bootcamp.sweeter.EmailService;
import lv.helloit.bootcamp.sweeter.security.PasswordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private final UserDAOInterface userDAO;
    private final EmailService emailService;
    private final PasswordService passwordService;

    public UserService(UserDAOInterface userDAO, EmailService emailService, PasswordService passwordService) {
        this.userDAO = userDAO;
        this.emailService = emailService;
        this.passwordService = passwordService;
    }

    public User registerUser(CreateUserDto createUserDto) {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setEmail(createUserDto.getEmail());
        user.setPasswordHash(passwordService.hash(createUserDto.getPassword()));
        userDAO.save(user);

        try {
            emailService.sendNewUserEmail(user.getEmail());
        } catch (SparkPostException e) {
            LOGGER.error("Failed to send greeting email for new user: " + user.getEmail(), e);
        }
        return user;
    }

    public Optional<User> getUserById(String userId) {
        return Optional.ofNullable(userDAO.getUserById(userId));
    }

    public Optional<User> getUserByEmail(String email) {
        return this.userDAO.getUserByEmail(email);
    }
}

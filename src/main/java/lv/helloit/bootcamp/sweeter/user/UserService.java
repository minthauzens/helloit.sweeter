package lv.helloit.bootcamp.sweeter.user;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User registerUser(CreateUserDto createUserDto) {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setEmail(createUserDto.getEmail());
        userDAO.save(user);
        return user;
    }
}

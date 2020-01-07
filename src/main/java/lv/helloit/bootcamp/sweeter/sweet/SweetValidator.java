package lv.helloit.bootcamp.sweeter.sweet;

import lv.helloit.bootcamp.sweeter.user.User;
import lv.helloit.bootcamp.sweeter.user.UserDontExistException;
import lv.helloit.bootcamp.sweeter.user.UserService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SweetValidator {

    private final UserService userService;

    public SweetValidator(UserService userService) {
        this.userService = userService;
    }

    public boolean validate(ChangeSweetDto newSweet) throws UserDontExistException {
        Optional<User> possibleUser = userService.getUserById(newSweet.getUserId());

        if (possibleUser.isEmpty()) {
            throw new UserDontExistException("User with id " + newSweet.getUserId() + " doesnt exist");
        }
        return true;
    }
}

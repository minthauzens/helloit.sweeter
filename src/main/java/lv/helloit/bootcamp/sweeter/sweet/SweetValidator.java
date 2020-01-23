package lv.helloit.bootcamp.sweeter.sweet;

import lv.helloit.bootcamp.sweeter.user.UserService;
import org.springframework.stereotype.Component;

@Component
public class SweetValidator {

    private final UserService userService;

    public SweetValidator(UserService userService) {
        this.userService = userService;
    }

    public boolean validate(ChangeSweetDto newSweet) {
        return true;
    }
}

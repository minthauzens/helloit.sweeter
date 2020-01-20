package lv.helloit.bootcamp.sweeter.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/sign-up")
    String signUp() { return "sign-up";}

    @PostMapping("/sign-up")
    String signUp(@Valid @ModelAttribute CreateUserDto user) {
        userService.registerUser(user);
        return "redirect:/sweets";
    }
}

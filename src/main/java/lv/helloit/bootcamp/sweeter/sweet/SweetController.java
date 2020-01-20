package lv.helloit.bootcamp.sweeter.sweet;

import lv.helloit.bootcamp.sweeter.user.UserDontExistException;
import lv.helloit.bootcamp.sweeter.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class SweetController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SweetController.class);
    private final SweetService sweetService;
    private final UserService userService;

    public SweetController(SweetService sweetService, UserService userService) {
        this.sweetService = sweetService;
        this.userService = userService;
    }

    @GetMapping("/create-sweet")
    String createSweet() {
        return "edit-sweet";
    }

    @GetMapping("/sweets")
    String getSweets(Model model) {
        LOGGER.info("Get sweets endpoint called");
        model.addAttribute("sweets", sweetService.getAllSweets());
        return "sweets";
    }

    @GetMapping("/sweet/{id}")
    String getSweet(Model model, @PathVariable("id") Long id) {
        Optional<SweetDto> optionalSweet = sweetService.getSweetById(id);

        if (optionalSweet.isPresent()) {
            model.addAttribute("sweet", optionalSweet.get());
            return "sweet";
        } else {
            return "redirect:/sweets";
        }

    }

    @PostMapping("/sweet/{id}")
    String updateSweet(Model model,
                       @PathVariable("id") Long id,
                       @Valid @ModelAttribute ChangeSweetDto sweet,
                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String wrongField = bindingResult.getFieldErrors().get(0).getField();
            model.addAttribute(wrongField + "_err", true);

            Optional<SweetDto> sweet1 = sweetService.getSweetById(id);
            sweet1.ifPresent(value -> model.addAttribute("sweet", value));
            return "sweet";
        } else {
            this.sweetService.update(id, sweet);
            return "redirect:/sweets";
        }
    }

    @PostMapping("/sweet")
    String create(Model model,
                  @Valid @ModelAttribute ChangeSweetDto sweetDto,
                  BindingResult bindingResult,
                  Authentication authentication
    ) throws UserDontExistException {
        if (bindingResult.hasErrors()) {
            String wrongField = bindingResult.getFieldErrors().get(0).getField();
            model.addAttribute(wrongField + "_err", true);
            return "edit-sweet";
        } else {
            this.sweetService.addSweet(sweetDto, authentication.getName());
            return "redirect:/sweets";
        }
    }
}

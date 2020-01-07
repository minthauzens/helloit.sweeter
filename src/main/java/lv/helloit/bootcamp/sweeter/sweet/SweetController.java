package lv.helloit.bootcamp.sweeter.sweet;

import lv.helloit.bootcamp.sweeter.user.UserDontExistException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    public SweetController(SweetService sweetService) {
        this.sweetService = sweetService;
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
        Optional<Sweet> optionalSweet = sweetService.getSweetById(id);

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

            Optional<Sweet> sweet1 = sweetService.getSweetById(id);
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
                  BindingResult bindingResult) throws UserDontExistException {
        if (bindingResult.hasErrors()) {
            String wrongField = bindingResult.getFieldErrors().get(0).getField();
            model.addAttribute(wrongField + "_err", true);
            return "edit-sweet";
        } else {
            this.sweetService.addSweet(sweetDto);
            return "redirect:/sweets";
        }
    }
}

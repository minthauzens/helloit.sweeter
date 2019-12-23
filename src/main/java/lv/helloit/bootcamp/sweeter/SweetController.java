package lv.helloit.bootcamp.sweeter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class SweetController {

    private final SweetService sweetService;

    public SweetController(SweetService sweetService) {
        this.sweetService = sweetService;
    }

    @GetMapping("/sweets")
    String getSweets(Model model) {
        model.addAttribute("sweets", sweetService.getAllSweets());
        return "sweets";
    }

    @GetMapping("/sweet/{id}")
    String getSweet(Model model, @PathVariable("id") Long id) {
        Optional<Sweet> optionalSweet = sweetService.getSweetById(id);
        optionalSweet.ifPresent(sweet -> model.addAttribute("sweet", sweet));
        return "sweet";
    }

    @PostMapping("/sweet/{id}")
    String updateSweet(Model model, @PathVariable("id") Long id,
                       @Valid @ModelAttribute ChangeSweetDto sweet,
                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println("ERROR PANIC");
            String wrongField = bindingResult.getFieldErrors().get(0).getField();
            model.addAttribute(wrongField + "_err", "ERROR");
        } else {
            this.sweetService.update(id, sweet);
        }
        Optional<Sweet> optionalSweet = sweetService.getSweetById(id);
        optionalSweet.ifPresent(resultSweet -> model.addAttribute("sweet", resultSweet));
        return "sweet";
    }

}

package lv.helloit.bootcamp.sweeter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TestController {

    @GetMapping({"/", "/hello"})
    public String hello(Model model, @RequestParam(value="name", defaultValue="World") String name) {
        model.addAttribute("name", name);
        model.addAttribute("numbers", List.of("abs", "basd", "asdasd"));
        return "hello";
    }
}

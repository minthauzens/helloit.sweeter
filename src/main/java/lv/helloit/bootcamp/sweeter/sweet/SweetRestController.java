package lv.helloit.bootcamp.sweeter.sweet;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rest")
public class SweetRestController {
    private final SweetService service;

    public SweetRestController(SweetService service) {
        this.service = service;
    }

    @GetMapping("/getSweets")
    public List<SweetDto> getAllSweets() {
        return this.service.getAllSweets();
    }

    @DeleteMapping("/sweet/{id}")
    public void deleteSweet(@PathVariable("id") String id) {
        this.service.deleteSweetById(id);
    }

    @DeleteMapping("/deleteSweets")
    public void deleteAllSweets() {
        this.service.deleteAllSweets();
    }

    @PostMapping("/addSweet")
    public ResponseEntity<Sweet> addSweet(@Valid @RequestBody ChangeSweetDto newSweet) {
//        FIXME
        return new ResponseEntity<>(this.service.addSweet(newSweet, null), HttpStatus.CREATED);
    }

    @PutMapping("/sweet/{id}")
    public void update(@PathVariable("id") String id, @Valid @RequestBody ChangeSweetDto newSweet) {
        this.service.update(id, newSweet);
    }
}

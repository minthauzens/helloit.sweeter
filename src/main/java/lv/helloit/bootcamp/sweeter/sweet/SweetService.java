package lv.helloit.bootcamp.sweeter.sweet;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SweetService {
    List<Sweet> sweets = new ArrayList<>();
    private long idCounter = 1L;

    public Sweet addSweet(ChangeSweetDto newSweet) {
        Sweet sweet = new Sweet();

        sweet.setId(this.idCounter);
        this.idCounter++;

        LocalDateTime currentTime = LocalDateTime.now();
        sweet.setDatePosted(currentTime);
        sweet.setDateLastUpdate(currentTime);

        sweet.setAuthor(newSweet.getAuthor());
        sweet.setContent(newSweet.getContent());
        this.sweets.add(sweet);
        return sweet;
    }

    public void update(Long id, ChangeSweetDto newSweet) {
        for (Sweet existingSweet : this.sweets) {
            if (existingSweet.getId().equals(id)) {
                existingSweet.setAuthor(newSweet.getAuthor());
                existingSweet.setContent(newSweet.getContent());
                existingSweet.setDateLastUpdate(LocalDateTime.now());
                break;
            }
        }
    }

    public Optional<Sweet> getSweetById(Long sweetId) {
        return this.sweets.stream()
                .filter(sweet -> sweet.getId().equals(sweetId))
                .findFirst();

    }

    public List<Sweet> getSweetsByAuthor(String author) {
        return this.sweets.stream()
                .filter(sweet -> sweet.getAuthor().equals(author))
                .collect(Collectors.toList());
    }

    public List<Sweet> getAllSweets() {
        return this.sweets;
    }

    public void deleteSweetById(Long sweetId) {
        Optional<Sweet> optionalSweet =  getSweetById(sweetId);
        optionalSweet.ifPresent(this::deleteSweet);
    }

    public void deleteSweet(Sweet sweet) {
        this.sweets.remove(sweet);
    }

    public void deleteAllSweets() {
        this.sweets.clear();
    }
}


package lv.helloit.bootcamp.sweeter;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SweetService {
    List<Sweet> sweets = new ArrayList<>();
    private long idCounter = 1L;

    public Sweet addSweet(Sweet newSweet) {
        newSweet.setId(idCounter);
        idCounter++;
        LocalDateTime currentTime = LocalDateTime.now();
        newSweet.setDatePosted(currentTime);
        newSweet.setDateLastUpdate(currentTime);
        this.sweets.add(newSweet);
        return newSweet;
    }

    public void update(Long id, Sweet newSweet) {
        for (Sweet existingSweet : this.sweets) {
            if (existingSweet.getId().equals(id)) {
                existingSweet.setDateLastUpdate(LocalDateTime.now());
                existingSweet.setAuthor(newSweet.getAuthor());
                existingSweet.setContent(newSweet.getContent());
                break;
            }
        }
    }

    public Optional<Sweet> getSweetById(Long sweetId) {
        return this.sweets.stream()
                .filter(sweet -> sweet.getId().equals(sweetId))
                .findFirst();

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


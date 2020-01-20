package lv.helloit.bootcamp.sweeter.sweet;

import lombok.RequiredArgsConstructor;
import lv.helloit.bootcamp.sweeter.sweet.SweetDto.SweetDtoBuilder;
import lv.helloit.bootcamp.sweeter.user.User;
import lv.helloit.bootcamp.sweeter.user.UserDontExistException;
import lv.helloit.bootcamp.sweeter.user.UserService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//@RequiredArgsConstructor
@Service
public class SweetService {
    private final SweetValidator validator;
    private final UserService userService;

    List<Sweet> sweets = new ArrayList<>();
    private long idCounter = 1L;

    public SweetService(SweetValidator validator, UserService userService) {
        this.validator = validator;
        this.userService = userService;
    }

    public Sweet addSweet(ChangeSweetDto newSweet, String authorEmail) throws UserDontExistException {
        validator.validate(newSweet);

        Sweet sweet = new Sweet();

        sweet.setId(this.idCounter);
        this.idCounter++;

        LocalDateTime currentTime = LocalDateTime.now();
        sweet.setDatePosted(currentTime);
        sweet.setDateLastUpdate(currentTime);

        var user = userService.getUserByEmail(authorEmail).get();

        sweet.setContent(newSweet.getContent());
        sweet.setUserId(user.getId());
        this.sweets.add(sweet);
        return sweet;
    }

    public void update(Long id, ChangeSweetDto newSweet) {
        for (Sweet existingSweet : this.sweets) {
            if (existingSweet.getId().equals(id)) {
                existingSweet.setContent(newSweet.getContent());
                existingSweet.setDateLastUpdate(LocalDateTime.now());
                break;
            }
        }
    }

    @Cacheable("sweetById")
    public Optional<SweetDto> getSweetById(Long sweetId) {
        return this.sweets.stream()
                .filter(sweet -> sweet.getId().equals(sweetId))
                .map(this::mapSweetToSweetDto)
                .findFirst();
    }

    public List<Sweet> getSweetsByUserId(String userId) {
        return this.sweets.stream()
                .filter(sweet -> sweet.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

    public List<SweetDto> getAllSweets() {
        List<SweetDto> result = new ArrayList<>();
        for (Sweet sweet : this.sweets) {
            SweetDto dto = mapSweetToSweetDto(sweet);
            result.add(dto);
        }
        return result;
    }

    public void deleteSweetById(Long sweetId) {
        Optional<Sweet> optionalSweet = this.sweets.stream()
                .filter(sweet -> sweet.getId().equals(sweetId))
                .findFirst();
        optionalSweet.ifPresent(this::deleteSweet);
    }

    public void deleteSweet(Sweet sweet) {
        this.sweets.remove(sweet);
    }

    public void deleteAllSweets() {
        this.sweets.clear();
    }

    private SweetDto mapSweetToSweetDto(Sweet sweet) {
        Optional<User> user = userService.getUserById(sweet.getUserId());
        String email = "unknown";
        if (user.isPresent()) {
            email = user.get().getEmail();
        }

        return new SweetDtoBuilder()
                .id(sweet.getId())
                .content(sweet.getContent())
                .userId(sweet.getUserId())
                .userEmail(email)
                .dateLastUpdate(sweet.getDateLastUpdate())
                .datePosted(sweet.getDatePosted())
                .build();
    }
}


package lv.helloit.bootcamp.sweeter.sweet;

import lv.helloit.bootcamp.sweeter.sweet.SweetDto.SweetDtoBuilder;
import lv.helloit.bootcamp.sweeter.user.User;
import lv.helloit.bootcamp.sweeter.user.UserService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

//@RequiredArgsConstructor
@Service
public class SweetService {
    private final SweetValidator validator;
    private final UserService userService;
    private final SweetDao sweetDao;

    public SweetService(SweetValidator validator, UserService userService, SweetDao sweetDao) {
        this.validator = validator;
        this.userService = userService;
        this.sweetDao = sweetDao;
    }

    public List<SweetDto> getAllSweets() {
        List<SweetDto> result = new ArrayList<>();
        for (Sweet sweet : sweetDao.findAll()) {
            SweetDto dto = mapSweetToSweetDto(sweet);
            result.add(dto);
        }
        return result;
    }

//    @Cacheable("sweetById")
    public Optional<SweetDto> getSweetById(String sweetId) {
        return sweetDao.findById(sweetId).map(this::mapSweetToSweetDto);
    }

    public Sweet addSweet(ChangeSweetDto newSweet, String authorEmail) {
        validator.validate(newSweet);

        Sweet sweet = new Sweet();

        sweet.setId(UUID.randomUUID().toString());
        sweet.setContent(newSweet.getContent());

        LocalDateTime currentTime = LocalDateTime.now();
        sweet.setDatePosted(currentTime);
        sweet.setDateLastUpdate(currentTime);

        var user = userService.getUserByEmail(authorEmail).get();
        sweet.setUserId(user.getId());

        sweetDao.save(sweet);
        return sweet;
    }

    public void update(String id, ChangeSweetDto newSweet) {
        validator.validate(newSweet);

        Optional<Sweet> optionalSweet = sweetDao.findById(id);

        if (optionalSweet.isPresent()) {
            Sweet sweet = optionalSweet.get();
            sweet.setContent(newSweet.getContent());
            sweetDao.save(sweet);
        }

    }

    public List<Sweet> getSweetsByUserId(String userId) {
        return sweetDao.findAllByUserId(userId);
    }

    public void deleteSweetById(String sweetId) {
        sweetDao.deleteById(sweetId);
    }

    public void deleteSweet(Sweet sweet) {
        sweetDao.delete(sweet);
    }

    public void deleteAllSweets() {
        sweetDao.deleteAll();
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


package lv.helloit.bootcamp.sweeter.sweet;

import lv.helloit.bootcamp.sweeter.sweet.ChangeSweetDto;
import lv.helloit.bootcamp.sweeter.sweet.Sweet;
import lv.helloit.bootcamp.sweeter.sweet.SweetService;
import lv.helloit.bootcamp.sweeter.sweet.SweetValidator;
import lv.helloit.bootcamp.sweeter.user.UserDontExistException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SweetServiceTest {
    SweetService service;

    @Mock
    private SweetValidator validator;

    @BeforeEach
    void setUp() {
        service = new SweetService(validator);
    }

    @Test
    void shouldCreateAndGetSweet() throws UserDontExistException {
        ChangeSweetDto sweet = new ChangeSweetDto();
        sweet.setContent("Test content");
        sweet.setUserId("John Doe");
        service.addSweet(sweet);

        List<Sweet> existingSweets = service.getAllSweets();

        assertEquals(1, existingSweets.size());
        Sweet existingSweet = existingSweets.get(0);

        assertEquals("John Doe", existingSweet.getUserId());
        assertEquals("Test content", existingSweet.getContent());
        assertEquals(1L, existingSweet.getId());
        assertNotNull(existingSweet.getDatePosted());
        assertNotNull(existingSweet.getDateLastUpdate());
    }

    @Test
    void shouldIncreaseIdsForNewSweets() throws UserDontExistException {
        ChangeSweetDto sw1 = new ChangeSweetDto();
        ChangeSweetDto sw2 = new ChangeSweetDto();

        service.addSweet(sw1);
        service.addSweet(sw2);

        List<Sweet> sweets = service.getAllSweets();
        assertEquals(2, sweets.size());
        assertEquals(1L, sweets.get(0).getId());
        assertEquals(2L, sweets.get(1).getId());

//        List<Long> ids = sweets.stream().map(Sweet::getId).collect(Collectors.toList());
//        assertThat(ids).containsExactlyInAnyOrder()
    }

    @Test
    void shouldThrowExceptionIfValidationThrewException() throws UserDontExistException {
        ChangeSweetDto sweet1 = new ChangeSweetDto();

        when(validator.validate(sweet1)).thenThrow(new UserDontExistException("Test error message"));

        Assertions.assertThatThrownBy(() -> service.addSweet(sweet1))
                .hasMessage("Test error message")
                .isInstanceOf(UserDontExistException.class);
    }
}
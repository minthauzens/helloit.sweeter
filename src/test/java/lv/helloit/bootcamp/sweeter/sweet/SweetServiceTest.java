package lv.helloit.bootcamp.sweeter.sweet;

import lv.helloit.bootcamp.sweeter.user.CreateUserDto;
import lv.helloit.bootcamp.sweeter.user.User;
import lv.helloit.bootcamp.sweeter.user.UserDontExistException;
import lv.helloit.bootcamp.sweeter.user.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SweetServiceTest {
    SweetService service;

    @Mock
    private SweetDao sweetDao;

    @Mock
    private UserService userService;

    @Mock
    private SweetValidator validator;

    @BeforeEach
    void setUp() {
        service = new SweetService(validator, userService, sweetDao);

        CreateUserDto createUserDto = new CreateUserDto();
        createUserDto.setEmail("test@test.com");
        createUserDto.setPassword("12345678");
        userService.registerUser(createUserDto);
    }

    @Test
    @WithMockUser("test@test.com")
    void shouldCreateAndGetSweet() {
        User user = new User();
        user.setId("1");
        user.setEmail("test@test.com");
        user.setPasswordHash("asdasdasd");
        when(userService.getUserByEmail("test@test.com")).thenReturn(Optional.of(user));

        ChangeSweetDto sweet = new ChangeSweetDto();
        sweet.setContent("Test content");
//        sweet.setUserId("John Doe");
        service.addSweet(sweet, "test@test.com");

        List<SweetDto> existingSweets = service.getAllSweets();

        assertEquals(1, existingSweets.size());
        SweetDto existingSweet = existingSweets.get(0);

        assertEquals("Test content", existingSweet.getContent());
        assertEquals(1L, existingSweet.getId());
        assertNotNull(existingSweet.getDatePosted());
        assertNotNull(existingSweet.getDateLastUpdate());
    }

    @Test
    void shouldIncreaseIdsForNewSweets() {
        User user = new User();
        user.setId("1");
        user.setEmail("test@test.com");
        user.setPasswordHash("asdasdasd");
        when(userService.getUserByEmail("test@test.com")).thenReturn(Optional.of(user));

        ChangeSweetDto sw1 = new ChangeSweetDto();
        ChangeSweetDto sw2 = new ChangeSweetDto();

        service.addSweet(sw1, "test@test.com");
        service.addSweet(sw2, "test@test.com");

        List<SweetDto> sweets = service.getAllSweets();
        assertEquals(2, sweets.size());
//        assertEquals(1L, sweets.get(0).getId());
//        assertEquals(2L, sweets.get(1).getId());

//        List<Long> ids = sweets.stream().map(Sweet::getId).collect(Collectors.toList());
//        assertThat(ids).containsExactlyInAnyOrder()
    }

    @Test
    void shouldThrowExceptionIfValidationThrewException() throws UserDontExistException {
        ChangeSweetDto sweet1 = new ChangeSweetDto();

        when(validator.validate(sweet1)).thenThrow(new UserDontExistException("Test error message"));

        Assertions.assertThatThrownBy(() -> service.addSweet(sweet1, "test@test.com"))
                .hasMessage("Test error message")
                .isInstanceOf(UserDontExistException.class);
    }
}
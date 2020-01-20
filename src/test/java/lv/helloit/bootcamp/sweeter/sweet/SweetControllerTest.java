package lv.helloit.bootcamp.sweeter.sweet;

import lv.helloit.bootcamp.sweeter.user.CreateUserDto;
import lv.helloit.bootcamp.sweeter.user.User;
import lv.helloit.bootcamp.sweeter.user.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SweetControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SweetService sweetService;

    @Autowired
    private UserService userService;

    @AfterEach
    void tearDown() {
        this.sweetService.deleteAllSweets();
    }

    @Test
    @WithMockUser("test@test.com")
    void shouldGetAllSweets() throws Exception {
        CreateUserDto createUserDto = new CreateUserDto();
        createUserDto.setEmail("test@test.com");
        createUserDto.setPassword("12345678");
        userService.registerUser(createUserDto);

        ChangeSweetDto sweet = new ChangeSweetDto();
        sweet.setContent("Test");
        Sweet savedSweet = sweetService.addSweet(sweet, createUserDto.getEmail());

        mockMvc.perform(get("/sweets"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("sweets",
                        hasItems(
                                anyOf(
                                        hasProperty("id", is(savedSweet.getId()))
                                ))));
    }
}
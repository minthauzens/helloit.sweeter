package lv.helloit.bootcamp.sweeter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class SweetControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SweetService sweetService;

    @AfterEach
    void tearDown() {
        this.sweetService.deleteAllSweets();
    }

    @Test
    void shouldGetAllSweets() throws Exception {
        Sweet sweet = new Sweet();
        sweet.setContent("Test");
        sweet.setAuthor("Test");
        Sweet savedSweet = sweetService.addSweet(sweet);

        mockMvc.perform(get("/sweets"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("sweets",
                        hasItems(
                                anyOf(
                                        hasProperty("id", is(savedSweet.getId()))
                                ))));
    }
}
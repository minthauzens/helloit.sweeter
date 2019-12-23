package lv.helloit.bootcamp.sweeter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SweetServiceTest {
    SweetService service;

    @BeforeEach
    void setUp() {
        service = new SweetService();
    }

    @Test
    void shouldCreateAndGetSweet() {
        ChangeSweetDto sweet = new ChangeSweetDto();
        sweet.setContent("Test content");
        sweet.setAuthor("John Doe");
        service.addSweet(sweet);

        List<Sweet> existingSweets = service.getAllSweets();

        assertEquals(1, existingSweets.size());
        Sweet existingSweet = existingSweets.get(0);

        assertEquals("John Doe", existingSweet.getAuthor());
        assertEquals("Test content", existingSweet.getContent());
        assertEquals(1L, existingSweet.getId());
        assertNotNull(existingSweet.getDatePosted());
        assertNotNull(existingSweet.getDateLastUpdate());
    }

    @Test
    void shouldIncreaseIdsForNewSweets() {
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
}
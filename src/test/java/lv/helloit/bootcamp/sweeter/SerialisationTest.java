package lv.helloit.bootcamp.sweeter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lv.helloit.bootcamp.sweeter.sweet.Sweet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

@SpringBootTest
class SerialisationTest {

    @Autowired
    private ObjectMapper mapper;

    @Test
    void shouldSerializeAndDeSerializeSweet() throws JsonProcessingException {
        Sweet sweet = new Sweet();
//        sweet.setId(1L);
        sweet.setUserId("John Doe");
        sweet.setContent("Test sweet");
        LocalDateTime currentTime = LocalDateTime.now();
        sweet.setDateLastUpdate(currentTime);
        sweet.setDatePosted(currentTime);

        String json = mapper.writeValueAsString(sweet);

        Sweet deSerializedSweet = mapper.readValue(json, Sweet.class);
        System.out.println(deSerializedSweet);
        assertEquals(sweet, deSerializedSweet);
        // assertSame(sweet, deSerializedSweet); // this will fail, because mapper returns new object
    }
}

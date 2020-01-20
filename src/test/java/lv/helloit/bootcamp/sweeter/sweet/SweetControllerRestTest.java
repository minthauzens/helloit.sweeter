//package lv.helloit.bootcamp.sweeter.sweet;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.web.server.LocalServerPort;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//class SweetControllerRestTest {
//
//    @LocalServerPort
//    private int port;
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    @AfterEach
//    void tearDown() {
//        deleteSweets();
//    }
//
//    @Test
//    void shouldPostAndGetSweet() {
//        Sweet sweet = addSweet("1");
//
//        List<Sweet> sweets = getSweets();
//
//        assertEquals(1, sweets.size());
//        var fetchedSweet = sweets.get(0);
//        assertEquals(sweet.getId(), fetchedSweet.getId());
////        assertEquals("Me", fetchedSweet.getUserId());
//        assertEquals("Test content 1", fetchedSweet.getContent());
//    }
//
//    @Test
//    void shouldPutAndUpdateSweet() {
//        Sweet sweet = addSweet("1");
//
//        Sweet newSweet = new Sweet();
////        newSweet.setUserId("Max");
//        newSweet.setContent("Test content 2");
//        // if author or content isn't passed will update to null, probably needs to be fixed in SweetService
//        Long sweetId = sweet.getId();
//
//
//        restTemplate.put(getBaseUrl() + "/sweet/" + sweetId, newSweet);
//        List<Sweet> sweets = getSweets();
//        Sweet fetchedSweet = sweets.get(0);
//        assertEquals(1, sweets.size());
//        assertEquals("Max", fetchedSweet.getUserId());
//        assertEquals("Test content 2", fetchedSweet.getContent());
//    }
//
//    @Test
//    void shouldDeleteAllSweets() {
//        addSweet("1");
//        addSweet("2");
//
//        List<Sweet> sweetsBeforeDeletion = getSweets();
//
//        assertEquals(2, sweetsBeforeDeletion.size());
//        assertEquals("Test content 1", sweetsBeforeDeletion.get(0).getContent());
//        assertEquals("Test content 2", sweetsBeforeDeletion.get(1).getContent());
//        // is this valid because of ordering?
//
//        deleteSweets();
//        List<Sweet> sweetsAfterDeletion = getSweets();
//        assertEquals(0, sweetsAfterDeletion.size());
//    }
//
//    private void deleteSweets() {
//        restTemplate.delete(getBaseUrl() + "/deleteSweets");
//    }
//
//    private List<Sweet> getSweets() {
//        return restTemplate.getForObject(getBaseUrl() + "/getSweets", SweetList.class);
//    }
//
//    private Sweet addSweet(String uniqueModifier) {
//        Sweet sweet = new Sweet();
////        sweet.setUserId("Me");
//        sweet.setContent("Test content " + uniqueModifier);
//
//        return restTemplate.postForObject(getBaseUrl() + "/addSweet", sweet, Sweet.class);
//    }
//
//    private String getBaseUrl() {
//        return "http://localhost:" + port + "/rest";
//    }
//}
//
//class SweetList extends ArrayList<Sweet> {
//}
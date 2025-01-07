package programmerzamannow.webmvc.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloControllerIntegrationTest {
    @Autowired
    TestRestTemplate restTemplate;

    @LocalServerPort
    private Integer port;

    @Test
    void testGuest() {
        String response = restTemplate.getForEntity("http://localhost:" + port + "/hello", String.class)
                .getBody();
        System.out.println(response);
        Assertions.assertNotNull(response);
        Assertions.assertEquals("Hello Guest", response.trim());

    }

    @Test
    void testName() {
        String response = restTemplate.getForEntity("http://localhost:" + port + "/hello?name=Shiro", String.class)
                .getBody();
        System.out.println(response);
        Assertions.assertNotNull(response);
        Assertions.assertEquals("Hello Shiro", response.trim());

    }
}

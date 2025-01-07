package programmerzamannow.webmvc.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HelloServiceTest {

    @Autowired
    private HelloService helloService;

    @Test
    void testHello() {
        Assertions.assertEquals("Hello Guest", helloService.hello(null));
        Assertions.assertEquals("Hello Shiro", helloService.hello("Shiro"));
    }
}

package programmerzamannow.aop.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

@SpringBootTest
public class HelloServiceTest {

    @Autowired
    private HelloService helloService;

    @Test
    void test() {
        Assertions.assertEquals("Hello Shiro", helloService.hello("Shiro"));
        Assertions.assertEquals("Bye Shiro", helloService.bye("Shiro"));
        Assertions.assertEquals("Test by Shiro", helloService.test("Shiro"));
    }
}

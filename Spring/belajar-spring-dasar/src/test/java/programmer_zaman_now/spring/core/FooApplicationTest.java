package programmer_zaman_now.spring.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import programmer_zaman_now.spring.core.application.FooApplication;
import programmer_zaman_now.spring.core.data.Foo;

@SpringBootTest(classes = FooApplication.class)
public class FooApplicationTest {
    @Autowired
    Foo foo;

    @Test
    void testFoo() {
        Assertions.assertNotNull(foo);
    }
}

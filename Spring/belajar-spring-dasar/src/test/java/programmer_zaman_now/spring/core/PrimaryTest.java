package programmer_zaman_now.spring.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import programmer_zaman_now.spring.core.data.Foo;

public class PrimaryTest {
    private ApplicationContext context;
    @BeforeEach
    void setUp() {
        context = new AnnotationConfigApplicationContext(PrimaryConfiguration.class);
    }

    @Test
    void testCreatePrimary() {
        Foo foo = context.getBean(Foo.class);
        Foo foo1 = context.getBean("foo1", Foo.class);
        Foo foo2 = context.getBean("foo2", Foo.class);

        Assertions.assertNotSame(foo, foo2);
        Assertions.assertSame(foo, foo1);
    }
}

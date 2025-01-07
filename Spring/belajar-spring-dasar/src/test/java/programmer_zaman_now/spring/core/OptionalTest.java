package programmer_zaman_now.spring.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import programmer_zaman_now.spring.core.data.Foo;
import programmer_zaman_now.spring.core.data.FooBar;

public class OptionalTest {
    
    private ConfigurableApplicationContext context;

    @BeforeEach
    void setUp() {
        context = new AnnotationConfigApplicationContext(OptionalConfiguration.class);
        context.registerShutdownHook();
    }

    @Test
    void testOptional() {
        Foo foo = context.getBean(Foo.class);
        FooBar fooBar = context.getBean(FooBar.class);

        Assertions.assertSame(foo, fooBar.getFoo());
        Assertions.assertNull(fooBar.getBar());
    }
}

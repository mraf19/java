package programmer_zaman_now.spring.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import programmer_zaman_now.spring.core.data.Bar;
import programmer_zaman_now.spring.core.data.Foo;

public class ImportTest {
    ConfigurableApplicationContext context;

    @BeforeEach
    void setUp() {
        context = new AnnotationConfigApplicationContext(MainConfiguration.class);
        context.registerShutdownHook();
    }

    @Test
    void testImport() {
        Foo foo = context.getBean(Foo.class);
        Bar bar = context.getBean(Bar.class);
    }
}

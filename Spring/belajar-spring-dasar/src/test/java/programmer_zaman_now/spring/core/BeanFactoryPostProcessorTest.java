package programmer_zaman_now.spring.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import programmer_zaman_now.spring.core.data.Foo;
import programmer_zaman_now.spring.core.processor.FooBeanFactoryBeanProcessor;

public class BeanFactoryPostProcessorTest {

    @Configuration
    @Import({
            FooBeanFactoryBeanProcessor.class
    })
    public static class ConfigurationTest { }

    private ConfigurableApplicationContext context;

    @BeforeEach
    void setUp() {
        context = new AnnotationConfigApplicationContext(ConfigurationTest.class);
        context.registerShutdownHook();
    }

    @Test
    void testFoo() {
        Foo foo = context.getBean(Foo.class);

        Assertions.assertNotNull(foo);
    }
}

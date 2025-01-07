package programmer_zaman_now.spring.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import programmer_zaman_now.spring.core.data.Foo;

public class BeanTest {
    @Test
    void testcCreateBean() {
        ApplicationContext context = new AnnotationConfigApplicationContext(BeanConfiguration.class);

        Assertions.assertNotNull(context);
    }

    @Test
    void testGetBean() {
        ApplicationContext context = new AnnotationConfigApplicationContext(BeanConfiguration.class);

        var bean1 = context.getBean(Foo.class);
        var bean2 = context.getBean(Foo.class);

        Assertions.assertSame(bean1, bean2);
    }
}

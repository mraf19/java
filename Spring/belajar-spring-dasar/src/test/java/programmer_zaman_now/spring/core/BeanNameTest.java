package programmer_zaman_now.spring.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import programmer_zaman_now.spring.core.data.Foo;

public class BeanNameTest {
    private ApplicationContext context;
    @BeforeEach
    void setUp() {
        context = new AnnotationConfigApplicationContext(BeanNameConfiguration.class);
    }

    @Test
    void testBeanName() {
        Foo foo = context.getBean(Foo.class);
        Foo fooFirst = context.getBean("fooFirst", Foo.class);
        Foo fooSecond = context.getBean("fooSecond", Foo.class);

        Assertions.assertSame(foo, fooFirst);
        Assertions.assertNotSame(fooFirst, fooSecond);
    }
}

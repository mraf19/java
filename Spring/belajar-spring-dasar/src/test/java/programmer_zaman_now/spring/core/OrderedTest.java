package programmer_zaman_now.spring.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import programmer_zaman_now.spring.core.data.Car;
import programmer_zaman_now.spring.core.processor.IdGeneratorBeanPostProcessor;
import programmer_zaman_now.spring.core.processor.PrefixIdGeneratorBeanPostProcessor;

public class OrderedTest {
    @Configuration
    @Import({
            Car.class, IdGeneratorBeanPostProcessor.class, PrefixIdGeneratorBeanPostProcessor.class
    })
    public static class ConfigurationTest { }

    private ConfigurableApplicationContext context;

    @BeforeEach
    void setUp() {
        context = new AnnotationConfigApplicationContext(ConfigurationTest.class);
        context.registerShutdownHook();
    }

    @Test
    void testCar() {
        Car car = context.getBean(Car.class);
        System.out.println(car.getId());
        Assertions.assertNotNull(car.getId());
        Assertions.assertTrue(car.getId().startsWith("PREFIX - "));
    }
}

package programmer_zaman_now.spring.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import programmer_zaman_now.spring.core.listeners.LoginAgainSuccessListener;
import programmer_zaman_now.spring.core.listeners.LoginSuccessListener;
import programmer_zaman_now.spring.core.listeners.UserListener;
import programmer_zaman_now.spring.core.service.UserService;

public class EventListenerTest {
    @Configuration
    @Import({UserService.class, LoginSuccessListener.class, LoginAgainSuccessListener.class, UserListener.class})
    public static class ConfigurationTest {}

    private ConfigurableApplicationContext context;

    @BeforeEach
    void setUp() {
        this.context = new AnnotationConfigApplicationContext(ConfigurationTest.class);
        this.context.registerShutdownHook();
    }

    @Test
    void testUserService() {
        UserService userService = context.getBean(UserService.class);

        userService.login("rafli", "rafli");
        userService.login("lolo", "rafli");
        userService.login("rafli", "lolo");
        userService.login("lolo", "lolo");
    }
}

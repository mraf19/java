package programmer_zaman_now.spring.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import programmer_zaman_now.spring.core.service.AuthService;

public class AwareTest {
    @Configuration
    @Import({AuthService.class})
    public static class ConfigurationTest { }

    private ConfigurableApplicationContext context;

    @BeforeEach
    void setUp() {
        context = new AnnotationConfigApplicationContext(ConfigurationTest.class);
        context.registerShutdownHook();
    }

    @Test
    void authService() {
        AuthService authService = context.getBean(AuthService.class);

        Assertions.assertNotNull(authService.getContext());
        Assertions.assertSame(authService.getContext(), this.context);
        Assertions.assertSame(authService.getBeanName(), "programmer_zaman_now.spring.core.service.AuthService");
    }
}

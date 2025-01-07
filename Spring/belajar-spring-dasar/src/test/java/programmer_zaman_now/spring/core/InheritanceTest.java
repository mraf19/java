package programmer_zaman_now.spring.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import programmer_zaman_now.spring.core.service.MerchantService;
import programmer_zaman_now.spring.core.service.MerchantServiceImpl;

public class InheritanceTest {
    private ConfigurableApplicationContext context;

    @BeforeEach
    void setUp() {
        context = new AnnotationConfigApplicationContext(InheritanceConfiguration.class);
        context.registerShutdownHook();
    }

    @Test
    void testInheritance() {
        MerchantService merchantService = context.getBean(MerchantService.class);
        MerchantServiceImpl merchantServiceImpl = context.getBean(MerchantServiceImpl.class);

        Assertions.assertEquals(merchantServiceImpl, merchantService);
    }
}

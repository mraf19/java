package programmer_zaman_now.spring.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import programmer_zaman_now.spring.core.client.PaymentGatewayClient;

public class FactoryTest {
    private ConfigurableApplicationContext context;

    @BeforeEach
    void setUp() {
        context = new AnnotationConfigApplicationContext(FactoryConfiguration.class);
        context.registerShutdownHook();
    }

    @Test
    void testFactory() {
        PaymentGatewayClient client = context.getBean(PaymentGatewayClient.class);
        String endpoint = client.getEndpoint();
        String publicKey = client.getPublicKey();
        String privateKey = client.getPrivateKey();
        Assertions.assertEquals("https://google.com", endpoint);
        Assertions.assertEquals("public", publicKey);
        Assertions.assertEquals("private", privateKey);
    }
}

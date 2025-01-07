package programmer_zaman_now.spring.core;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import programmer_zaman_now.spring.core.data.Connection;
import programmer_zaman_now.spring.core.data.Server;

public class LifecycleTest {

    ConfigurableApplicationContext context;
    @BeforeEach
    void setUp() {
        context = new AnnotationConfigApplicationContext(LifecycleConfiguration.class);
        context.registerShutdownHook();
    }

    @AfterEach
    void tearDown() {
//        context.close();
    }

    @Test
    void connectionTest() {
        Connection connection = context.getBean(Connection.class);
    }

    @Test
    void serverTest() {
        Server server = context.getBean(Server.class);
    }
}

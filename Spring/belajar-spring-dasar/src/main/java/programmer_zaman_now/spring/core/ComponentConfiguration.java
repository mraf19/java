package programmer_zaman_now.spring.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import programmer_zaman_now.spring.core.data.MultiFoo;

@Configuration
@ComponentScan(value = {
        "programmer_zaman_now.spring.core.service",
        "programmer_zaman_now.spring.core.repository",
        "programmer_zaman_now.spring.core.configuration",
})
@Import({
        MultiFoo.class
})
public class ComponentConfiguration {
}

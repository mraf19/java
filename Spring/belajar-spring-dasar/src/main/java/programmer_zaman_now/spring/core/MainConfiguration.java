package programmer_zaman_now.spring.core;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import programmer_zaman_now.spring.core.configuration.BarConfiguration;
import programmer_zaman_now.spring.core.configuration.FooConfiguration;

@Configuration
@Import(value = {
        FooConfiguration.class,
        BarConfiguration.class
})
public class MainConfiguration {
}

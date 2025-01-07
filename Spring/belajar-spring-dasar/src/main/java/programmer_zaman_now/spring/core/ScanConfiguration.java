package programmer_zaman_now.spring.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "programmer_zaman_now.spring.core.configuration"
})
public class ScanConfiguration {
}

package programmer_zaman_now.spring.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import programmer_zaman_now.spring.core.data.Foo;

@Configuration
public class BeanConfiguration {
    @Bean
    public Foo foo(){
        Foo foo = new Foo();
        return foo;
    }
}

package programmer_zaman_now.spring.core.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import programmer_zaman_now.spring.core.data.Foo;

@Configuration
public class FooConfiguration {
    @Primary
    @Bean
    public Foo foo(){
        return new Foo();
    }
    @Bean
    public Foo foo2(){
        return new Foo();
    }
    @Bean
    public Foo foo3(){
        return new Foo();
    }
}

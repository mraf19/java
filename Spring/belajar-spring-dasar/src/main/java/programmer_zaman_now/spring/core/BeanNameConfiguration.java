package programmer_zaman_now.spring.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import programmer_zaman_now.spring.core.data.Foo;

@Configuration
public class BeanNameConfiguration {
    @Primary
    @Bean(name = "fooFirst")
    public Foo foo1(){
        Foo foo = new Foo();
        return foo;
    }

    @Bean(name = "fooSecond")
    public Foo foo2(){
        Foo foo = new Foo();
        return  foo;
    }
}

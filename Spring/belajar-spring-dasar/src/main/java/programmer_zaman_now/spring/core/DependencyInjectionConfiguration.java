package programmer_zaman_now.spring.core;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import programmer_zaman_now.spring.core.data.Bar;
import programmer_zaman_now.spring.core.data.Foo;
import programmer_zaman_now.spring.core.data.FooBar;

@Configuration
public class DependencyInjectionConfiguration {
    @Primary
    @Bean(name = "fooFirst")
    public Foo foo1(){
        return new Foo();
    }
    @Bean(name = "fooSecond")
    public Foo foo2(){
        return new Foo();
    }

    @Bean
    public Bar bar(){
        return new Bar();
    }

    @Bean
    public FooBar fooBar(@Qualifier("fooSecond") Foo foo, Bar bar){
        return new FooBar(foo, bar);
    }
}

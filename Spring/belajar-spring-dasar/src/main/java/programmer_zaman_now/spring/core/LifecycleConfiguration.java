package programmer_zaman_now.spring.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import programmer_zaman_now.spring.core.data.Connection;
import programmer_zaman_now.spring.core.data.Server;

@Configuration
public class LifecycleConfiguration {
    @Bean
    public Connection connection(){
        return new Connection();
    }

//    @Bean(initMethod = "start", destroyMethod = "stop")
    @Bean
    public Server server(){
        return new Server();
    }
}

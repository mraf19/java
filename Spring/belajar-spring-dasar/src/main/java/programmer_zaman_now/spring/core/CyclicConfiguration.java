package programmer_zaman_now.spring.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import programmer_zaman_now.spring.core.cyclic.CyclicA;
import programmer_zaman_now.spring.core.cyclic.CyclicB;
import programmer_zaman_now.spring.core.cyclic.CyclicC;

@Configuration
public class CyclicConfiguration {
    @Bean
    public CyclicA cyclicA(CyclicB cyclicB){
        return new CyclicA(cyclicB);
    }

    @Bean
    public CyclicB cyclicB(CyclicC cyclicC){
        return new CyclicB(cyclicC);
    }

    @Bean
    public CyclicC cyclicC(CyclicA cyclicA){
        return new CyclicC(cyclicA);
    }
}

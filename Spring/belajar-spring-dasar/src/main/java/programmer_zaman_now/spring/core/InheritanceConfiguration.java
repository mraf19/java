package programmer_zaman_now.spring.core;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import programmer_zaman_now.spring.core.service.MerchantServiceImpl;

@Configuration
@Import({
        MerchantServiceImpl.class
})
public class InheritanceConfiguration {
}

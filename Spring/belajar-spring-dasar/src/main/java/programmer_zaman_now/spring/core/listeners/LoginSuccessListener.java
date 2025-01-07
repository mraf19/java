package programmer_zaman_now.spring.core.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import programmer_zaman_now.spring.core.events.LoginSuccessEvent;
@Slf4j
@Component
public class LoginSuccessListener implements ApplicationListener<LoginSuccessEvent> {
    @Override
    public void onApplicationEvent(LoginSuccessEvent event) {
        log.info("Success login for user {}", event.getUser());
    }
}

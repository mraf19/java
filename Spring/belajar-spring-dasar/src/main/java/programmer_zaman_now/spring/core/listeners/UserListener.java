package programmer_zaman_now.spring.core.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import programmer_zaman_now.spring.core.events.LoginSuccessEvent;
@Slf4j
@Component
public class UserListener {
    @EventListener(classes = LoginSuccessEvent.class)
    public void onLoginSuccessEvent(LoginSuccessEvent event){
        log.info("Success login for user {}", event.getUser());
    }
    @EventListener(classes = LoginSuccessEvent.class)
    public void onLoginSuccessEvent2(LoginSuccessEvent event){
        log.info("Success login for user {}", event.getUser());
    }
    @EventListener(classes = LoginSuccessEvent.class)
    public void onLoginSuccessEvent3(LoginSuccessEvent event){
        log.info("Success login for user {}", event.getUser());
    }
}

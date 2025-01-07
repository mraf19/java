package programmer_zaman_now.spring.core.service;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;
import programmer_zaman_now.spring.core.data.User;
import programmer_zaman_now.spring.core.events.LoginSuccessEvent;

@Component
public class UserService implements ApplicationEventPublisherAware {
    private ApplicationEventPublisher applicationEventPublisher;
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public boolean login(String username, String password){
        if(isLoginSuccess(username, password)){
            applicationEventPublisher.publishEvent(new LoginSuccessEvent(new User(username)));
            return true;
        }
      return false;
    }

    public boolean isLoginSuccess(String username, String password){
        return "rafli".equals(username) && "rafli".equals(password);
    }


}

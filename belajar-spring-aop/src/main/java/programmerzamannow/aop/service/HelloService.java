package programmerzamannow.aop.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HelloService {

    public String hello(String name){
        log.info("Call HelloService.hello()");
        return "Hello " + name;
    }

    public String bye(String name){
        log.info("Call HelloService.bye()");
        return "Bye " + name;
    }

    public String test(String name){
        log.info("Call HelloService.test()");
        return "Test by " + name;
    }
}

package programmerzamannow.webmvc.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import programmerzamannow.webmvc.service.HelloService;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@Controller
public class HelloController {

    private final HelloService helloService;
    @Autowired
    public HelloController(HelloService helloService){
        this.helloService = helloService;
    }
    @GetMapping(path = "/hello")
    public void helloWorld(@RequestParam(name = "name", required = false) String name, HttpServletResponse response) throws IOException {
        String body = helloService.hello(name);
        response.getWriter().println(body);
    }

    @GetMapping(path = "/web/hello")
    public ModelAndView hello(@RequestParam(name = "name", required = false) String name){
        if(Objects.isNull(name)){
            return new ModelAndView("redirect:/web/hello?name=Guest");
        }
        return new ModelAndView("hello", Map.of(
                "title", "Belajar View",
                "name", name
        ));
    }
}

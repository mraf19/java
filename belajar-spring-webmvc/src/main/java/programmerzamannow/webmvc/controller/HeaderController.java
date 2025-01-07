package programmerzamannow.webmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HeaderController {

    @GetMapping(path = "/header/token")
    @ResponseBody
    public String getHeader(@RequestHeader(name = "X-TOKEN") String token){
        return token.equals("Shiro") ? "OK" : "KO";
    }
}

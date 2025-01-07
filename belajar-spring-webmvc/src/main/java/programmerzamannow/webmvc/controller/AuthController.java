package programmerzamannow.webmvc.controller;

import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import programmerzamannow.webmvc.model.User;

@Controller
public class AuthController {

    @PostMapping(path = "/auth/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> login(
            @RequestParam(name = "username") String username,
            @RequestParam(name = "password") String password,
            HttpServletResponse response,
            HttpServletRequest request
    ){
        if("shiro".equals(username) && "rahasia".equals(password)){
//            return new ResponseEntity<>("OK", HttpStatus.OK);
            Cookie cookie = new Cookie("Username", username);
            HttpSession session = request.getSession(true);

            session.setAttribute("user", new User((username)));
            cookie.setPath("/");
            response.addCookie(cookie);
            return ResponseEntity.status(HttpStatus.OK).body("OK");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("KO");
//            return new ResponseEntity<>("KO", HttpStatus.UNAUTHORIZED);
        }

    }

    @GetMapping(path = "/auth/user")
    public ResponseEntity<String> getUser(
            @CookieValue("username" )String username
    ){
        return ResponseEntity.status(HttpStatus.OK).body("Hello " + username);
    }
}

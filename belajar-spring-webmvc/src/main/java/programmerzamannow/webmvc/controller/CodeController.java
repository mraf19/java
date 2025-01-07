package programmerzamannow.webmvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class CodeController {

    @DeleteMapping(path = "/products/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(
            @PathVariable("id") Integer id
    ){
        //delete to db
    }
}

package programmerzamannow.webmvc.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class FormController {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @PostMapping(path = "/form/person", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    public String getPerson(
            @RequestParam(name = "name") String name,
            @RequestParam(name = "date") Date date,
            @RequestParam(name = "address") String address
    ){
        return "Success create person with name: " + name +
                ", Birthdate: " + dateFormat.format(date) +
                ", Address: " + address;
    }

    @PostMapping(
            path = "form/hello",
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.TEXT_HTML_VALUE
    )
    @ResponseBody
    public String getFormHello(@RequestParam(name = "name") String name){
        return """
                <html>
                    <head>Form Hello</head>
                    <body>
                        <h1>Hello $name</h1>
                    </body>
                </html>
                """.replace("$name", name);
    }
}

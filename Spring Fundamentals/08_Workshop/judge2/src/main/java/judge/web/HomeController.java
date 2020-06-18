package judge.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;


@Controller
public class HomeController {

    // client Get
    @GetMapping("/test")
    @ResponseBody
    public String test(){
        return "<h1>Test</h1>";
    }

    @GetMapping("/")
    public String index(HttpSession httpSession){
        return httpSession.getAttribute("user") != null ? "home" : "index";
    }
}

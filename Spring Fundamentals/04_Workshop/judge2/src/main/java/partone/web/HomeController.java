package partone.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HomeController {

    // client Get
    @GetMapping("/test")
    @ResponseBody
    public String test(){
        return "<h1>Test</h1>";
    }

    @GetMapping("/")
    public String index(){
        return "index";
    }
}

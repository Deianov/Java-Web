package bg.softuni.security.web;

import java.security.Principal;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @GetMapping(value = {"/", "/home"})
    public String home() {
        return "home";
    }

    @GetMapping("/admin")
    public ModelAndView admin(@AuthenticationPrincipal Principal principal) {
        ModelAndView mav = new ModelAndView("admin");
        mav.addObject("user", principal);
        return mav;
    }

    @GetMapping("/user")
    public String user(@AuthenticationPrincipal Principal principal, Model model) {
        model.addAttribute("user", principal);
        return "user";
    }
}

package vehicles.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vehicles.model.User;
import vehicles.service.AuthService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@Controller
@RequestMapping("/users")
public class AuthController {
    private static final Logger LOGGER = LogManager.getLogger(AuthController.class.getName());

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/register")
    public String register(@ModelAttribute("user")User user) {
        return "register";
    }

    @PostMapping("/register")
    public String registerNewUser(@Valid @ModelAttribute("user") User user, final BindingResult binding, RedirectAttributes redirectAttributes) {
        if (binding.hasErrors()) {
            LOGGER.error("Error registering user: {}", binding.getAllErrors());
            redirectAttributes.addFlashAttribute("user", user);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.user", binding);
            return "redirect:register";
        }
        try {
            authService.register(user);
        } catch (Exception ex) {
            LOGGER.error("Error registering user", ex);
            redirectAttributes.addFlashAttribute("user", user);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.user", binding);
            return "redirect:register";
        }

        return "redirect:login";
    }

    @GetMapping("/login")
    public String getLoginForm(Model model) {
        if (!model.containsAttribute("username")) {
            model.addAttribute("username", "");
        }
        if (!model.containsAttribute("password")) {
            model.addAttribute("password", "");
        }
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @ModelAttribute("redirectUrl") String redirectUrl,
                        RedirectAttributes redirectAttributes,
                        HttpSession session) {
        User loggedUser = authService.login(username, password);
        if (loggedUser == null) {
            String errors = "Invalid username or password.";
            redirectAttributes.addFlashAttribute("username", username);
            redirectAttributes.addFlashAttribute("errors", errors);
            return "redirect:login";
        } else {
            session.setAttribute("user", loggedUser);
            if(redirectUrl != null && redirectUrl.length() > 0 ) {
                return "redirect:" + redirectUrl;
            } else {
                return "redirect:/";
            }
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}

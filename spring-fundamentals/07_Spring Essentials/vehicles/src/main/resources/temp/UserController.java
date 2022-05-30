package vehicles.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import vehicles.model.User;
import vehicles.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user){
        // TODO: 05.06.2020 validate
        user.setFirstName("First");
        user.setLastName("Last");
        user.setPassword("1234");
        userService.createUser(user);
        return "redirect:/login";
    }
}

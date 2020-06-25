package judge.web;

import judge.constant.Constants;
import judge.model.service.UserServiceModel;
import judge.service.AuthenticationService;
import judge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/users/roles")
public class RoleController {

    private final UserService userService;
    private final AuthenticationService authenticationService;

    @Autowired
    public RoleController(UserService userService, AuthenticationService authenticationService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
    }


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String grantAuthorityForm(Model model,
                                     HttpSession session) {

        if (authenticationService.isAuthorizedUser(session, Constants.ROLE_ADMIN)) {
            if (!model.containsAttribute("users")) {
                model.addAttribute("users", userService.getUsers());
            }
            return "role-add";
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String grantAuthority(@RequestParam(value = "username", required = false) String userId,
                                 @RequestParam(value = "role", required = false) String role,
                                 HttpSession session,
                                 RedirectAttributes attributes) {

        if(userId.isBlank() || userId.isEmpty() || role == null){
            attributes.addFlashAttribute("errors", "Set username and role");
            return "redirect:/users/roles/add";
        }

        try {
            UserServiceModel admin = (UserServiceModel) session.getAttribute("user");
            userService.grantAuthority(admin, userId, role);
            attributes.addFlashAttribute("success", String.format("The user role is assigned to: %s", role));

        } catch (Exception ex) {
            return "redirect:/";
        }
        return "redirect:/users/roles/add";
    }
}

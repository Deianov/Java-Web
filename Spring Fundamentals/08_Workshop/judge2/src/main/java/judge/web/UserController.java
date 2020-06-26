package judge.web;

import judge.constant.Constants;
import judge.exception.AlreadyExistsException;
import judge.model.view.UserViewModel;
import judge.service.AuthenticationService;
import judge.service.HomeworkService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import judge.model.binding.UserRegisterBindingModel;
import judge.model.service.UserServiceModel;
import judge.service.UserService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final AuthenticationService authenticationService;
    private final HomeworkService homeworkService;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper, AuthenticationService authenticationService, HomeworkService homeworkService) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.authenticationService = authenticationService;
        this.homeworkService = homeworkService;
    }

    @GetMapping("/login")
    public String login(Model model) {
        if(model.containsAttribute("password")) {
            model.addAttribute("password", "");
        }
        return "login";
    }

    @PostMapping("/login")
    public String loginConfirm(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               RedirectAttributes redirectAttributes,
                               HttpSession httpSession) {
        try {
            UserServiceModel userServiceModel = userService.login(username, password);
            httpSession.setAttribute("user", userServiceModel);
            httpSession.setAttribute("userId", userServiceModel.getId());

        } catch (NoSuchElementException e) {
            redirectAttributes.addFlashAttribute("username", username);
            redirectAttributes.addFlashAttribute("errors", Constants.USER_INVALID);
            return "redirect:/users/login";
        }
        return "redirect:/";
    }

    @GetMapping("/register")
    public String register(Model model) {
        if (!model.containsAttribute("userRegisterBindingModel")) {
            model.addAttribute("userRegisterBindingModel", new UserRegisterBindingModel());
            model.addAttribute("unableToRegister", false);
        }
        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid @ModelAttribute("userRegisterBindingModel") UserRegisterBindingModel bindingModel,
                                  BindingResult result,
                                  RedirectAttributes attributes) {

        if(bindingModel == null || result == null || attributes == null) {
            return "redirect:/";
        }

        // add custom error
        if (!bindingModel.getPassword().equals(bindingModel.getConfirmPassword())) {
            result.rejectValue("confirmPassword", "error.userRegisterBindingModel", Constants.USER_PASSWORDS_DOES_NOT_MATCH_MESSAGE);
        }

        // create user if not errors
        if (!result.hasErrors()) {
            try {
                this.userService.registerUser(this.modelMapper.map(bindingModel, UserServiceModel.class));

            } catch (AlreadyExistsException ex) {
                // add service error
                result.rejectValue(ex.getField(), "error.userRegisterBindingModel", ex.getMessage());

            } catch (Exception ex) {
                return "redirect:/";
            }
        }

        // redirect and show errors: custom, binding or service
        if (result.hasErrors()) {
            attributes.addFlashAttribute("userRegisterBindingModel", bindingModel);
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", result);
            return "redirect:/users/register2";
        }
        return "redirect:/users/login";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:/";
    }

    @RequestMapping("/profile")
    public String currentProfile(Model model,
                                 HttpSession session) {

        if(!authenticationService.isAuthorizedUser(session)){
            return "redirect:/";
        }

        UserViewModel user = userService.getById((String) session.getAttribute("userId"));
        user.setHomework(homeworkService.getHomework((String) session.getAttribute("userId")));
        model.addAttribute("user", user);
        return "profile";
    }

    @RequestMapping("/profile/{id}")
    public String userProfile(Model model,
                              HttpSession session,
                              @PathVariable("id") String id) {

        if(!authenticationService.isAuthorizedUser(session, Constants.ROLE_ADMIN)){
            return "redirect:/users/profile";
        }

        UserViewModel user = userService.getById(id);
        user.setHomework(homeworkService.getHomework(id));
        model.addAttribute("user", user);
        return "profile";
    }
}

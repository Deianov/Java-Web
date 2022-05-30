package exam.web;

import exam.model.bind.UserRegisterBindModel;
import exam.service.UserService;
import exam.constant.Constants;
import exam.exception.AlreadyExistsException;
import exam.model.service.UserServiceModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/users")
public class UserController extends BaseController{
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
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
        boolean error = false;

        if(username == null || username.length() < 3 || username.length() > 20) {
            redirectAttributes.addFlashAttribute("errorUsername", Constants.USER_NAME_LENGTH_MESSAGE);
            error = true;
        }

        if(password == null || password.length() < 3 || password.length() > 20) {
            redirectAttributes.addFlashAttribute("errorPassword", Constants.USER_PASSWORD_LENGTH_MESSAGE);
            error = true;
        }

        if (error){
            redirectAttributes.addFlashAttribute("username", username);
            return "redirect:/users/login";
        }

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
        if (!model.containsAttribute("userRegisterBindModel")) {
            model.addAttribute("userRegisterBindModel", new UserRegisterBindModel());
            model.addAttribute("errors", false);
        }
        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid @ModelAttribute("userRegisterBindModel") UserRegisterBindModel bindingModel,
                                  BindingResult result,
                                  RedirectAttributes attributes) {

        // custom error
        if (!bindingModel.getPassword().equals(bindingModel.getConfirmPassword())) {
            result.rejectValue("confirmPassword", "error.userRegisterBindModel", Constants.USER_PASSWORDS_DOES_NOT_MATCH_MESSAGE);
        }

        // register user
        if (!result.hasErrors()) {
            try {
                this.userService.register(this.modelMapper.map(bindingModel, UserServiceModel.class));

            } catch (AlreadyExistsException ex) {
                // service error
                result.rejectValue(ex.getField(), "error.userRegisterBindModel", ex.getMessage());
            }
        }

        // redirect and show errors: custom, binding and service
        if (result.hasErrors()) {
            attributes.addFlashAttribute("userRegisterBindModel", bindingModel);
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindModel", result);
            return "redirect:/users/register";
        }
        return "redirect:/users/login";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession httpSession) {
        if(httpSession != null) {
            httpSession.invalidate();
        }
        return "redirect:/";
    }
}

package judge.web;

import judge.constant.Constants;
import judge.exception.AlreadyExistsException;
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
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {
        boolean doRedirect = false;

        if (bindingResult.hasErrors()) {
            doRedirect = true;
        } else if (!bindingModel.getPassword().equals(bindingModel.getConfirmPassword())) {
            doRedirect = true;
            bindingResult.rejectValue("confirmPassword", "error.userRegisterBindingModel", Constants.USER_PASSWORDS_DOES_NOT_MATCH_MESSAGE);
        } else {
            try {
                this.userService.registerUser(this.modelMapper.map(bindingModel, UserServiceModel.class));

            } catch (AlreadyExistsException ex) {
                doRedirect = true;
                bindingResult.rejectValue(ex.getField(), "error.userRegisterBindingModel", ex.getMessage());

            } catch (Exception ex) {
                doRedirect = true;
                redirectAttributes.addFlashAttribute("unableToRegister", Constants.USER_REGISTER_EXCEPTION_MESSAGE);
            }
        }

        if (doRedirect) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", bindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);
            return "redirect:/users/register";
        }
        return "redirect:/users/login";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:/";
    }
}

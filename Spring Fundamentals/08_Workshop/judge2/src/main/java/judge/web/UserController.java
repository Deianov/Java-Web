package judge.web;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import judge.model.binding.UserRegisterBindingModel;
import judge.model.binding.UserLoginBidingModel;
import judge.model.service.UserServiceModel;
import judge.service.UserService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/users") // prefix
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public ModelAndView loginConfirm(@Valid @ModelAttribute("user")UserLoginBidingModel userLoginBidingModel,
                                     BindingResult bindingResult,
                                     ModelAndView modelAndView,
                                     HttpSession httpSession){

        if(bindingResult.hasErrors()){
            modelAndView.setViewName("redirect:/users/login");
        } else {
            modelAndView.setViewName("redirect:/");
        }

        // TODO: 16.06.2020 validation

        try {
            UserServiceModel userServiceModel = userService
                    .login(userLoginBidingModel.getUsername(), userLoginBidingModel.getPassword());

            // TODO: 16.06.2020 session id ???
            httpSession.setAttribute("user", userServiceModel);
            httpSession.setAttribute("id", userServiceModel.getId());

        } catch (NoSuchElementException e) {
            modelAndView.setViewName("redirect:/users/login");
        }
        return modelAndView;
    }

    @GetMapping("/register")
    public String register(@ModelAttribute("userRegisterBindingModel") UserRegisterBindingModel userRegisterBindingModel,
                           Model model) {

        if (!model.containsAttribute("userRegisterBindingModel")) {
            model.addAttribute("userRegisterBindingModel", new UserRegisterBindingModel());
        }
        return "register";
    }

    @PostMapping("/register")
    public ModelAndView registerConfirm(@Valid @ModelAttribute("userRegisterBindingModel") UserRegisterBindingModel userRegisterBindingModel,
                                        BindingResult bindingResult,
                                        ModelAndView modelAndView,
                                        RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            modelAndView.setViewName("redirect:/users/register");

        } else {
            UserServiceModel model = this.userService
                    .registerUser(this.modelMapper.map(userRegisterBindingModel, UserServiceModel.class));

            modelAndView.setViewName("redirect:/users/login");
        }
        return modelAndView;
    }

    @RequestMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.invalidate();
        return "redirect:/";
    }
}

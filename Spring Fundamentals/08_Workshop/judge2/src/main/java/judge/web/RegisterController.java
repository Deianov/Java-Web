package judge.web;

import judge.constant.Constants;
import judge.exception.AlreadyExistsException;
import judge.model.binding.UserRegisterBindingModel;
import judge.model.service.UserServiceModel;
import judge.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

// test version of register

@Controller
@RequestMapping("users/register2")
public class RegisterController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public RegisterController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }


    @RequestMapping(method = RequestMethod.GET)
    public String handleGetRequest(Model model) {
        if (!model.containsAttribute("userRegisterBindingModel")) {
            model.addAttribute("userRegisterBindingModel", new UserRegisterBindingModel());
        }
        return "register2";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String handlePostRequest (@Valid @ModelAttribute("userRegisterBindingModel") UserRegisterBindingModel userRegisterBindingModel,
                                     BindingResult bindingResult,
                                     RedirectAttributes attributes) {

        // add custom error
        if (!userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {
            bindingResult.rejectValue("confirmPassword", "error.userRegisterBindingModel", Constants.USER_PASSWORDS_DOES_NOT_MATCH_MESSAGE);
        }

        // create user if not errors
        if (!bindingResult.hasErrors()) {
            try {
                this.userService.registerUser(this.modelMapper.map(userRegisterBindingModel, UserServiceModel.class));

            } catch (AlreadyExistsException ex) {
                // add service error
                bindingResult.rejectValue(ex.getField(), "error.userRegisterBindingModel", ex.getMessage());

            } catch (Exception ex) {
                return "redirect:/";
            }
        }

        // redirect and show errors: custom, binding or service
        if (bindingResult.hasErrors()) {
            attributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel);
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);
            return "redirect:/users/register2";
        }

        return "redirect:/users/login";
    }
}

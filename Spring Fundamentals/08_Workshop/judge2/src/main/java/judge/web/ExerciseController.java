package judge.web;

import judge.constant.Constants;
import judge.exception.AlreadyExistsException;
import judge.model.binding.ExerciseBindingModel;
import judge.model.entity.Role;
import judge.model.service.ExerciseServiceModel;
import judge.service.AuthenticationService;
import judge.service.ExerciseService;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/exercises")
public class ExerciseController {

    private final ExerciseService exerciseService;
    private final ModelMapper modelMapper;
    private final AuthenticationService authenticationService;

    @Autowired
    public ExerciseController(ExerciseService exerciseService, ModelMapper modelMapper, AuthenticationService authenticationService) {
        this.exerciseService = exerciseService;
        this.modelMapper = modelMapper;
        this.authenticationService = authenticationService;
    }

    @GetMapping("/add")
    public String addExercise(Model model,
                              HttpSession session) {

        try {
            if(authenticationService.isAuthorizedUser(session, Constants.ROLE_ADMIN)) {
                if (!model.containsAttribute("exercise")) {
                    model.addAttribute("exercise", new ExerciseBindingModel());
                }
                return "exercise-add";
            }

        } catch (Exception e) {
            session.setAttribute("error", e.getMessage());
            return "redirect:/app/error";
        }

        return "redirect:/";
    }

    @PostMapping("/add")
    public String addExercise(@Valid @ModelAttribute("exercise") ExerciseBindingModel bindingModel,
                                    BindingResult result,
                                    RedirectAttributes attributes) {

        if(!result.hasErrors()) {
            try {
                exerciseService.create(modelMapper.map(bindingModel, ExerciseServiceModel.class));

            } catch (AlreadyExistsException ex) {
                result.rejectValue(ex.getField(), "error.userRegisterBindingModel", ex.getMessage());
            }
        }

        if(result.hasErrors()) {
            attributes.addFlashAttribute("exercise", bindingModel);
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.exercise", result);
        }

        return "redirect:/exercises/add";
    }
}

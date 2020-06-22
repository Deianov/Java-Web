package judge.web;

import judge.model.binding.HomeworkBindingModel;
import judge.service.AuthenticationService;
import judge.service.HomeworkService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("/homework")
public class HomeworkController {

    private final HomeworkService homeworkService;
    private final ModelMapper mapper;
    private final AuthenticationService authenticationService;

    @Autowired
    public HomeworkController(HomeworkService homeworkService, ModelMapper mapper, AuthenticationService authenticationService) {
        this.homeworkService = homeworkService;
        this.mapper = mapper;
        this.authenticationService = authenticationService;
    }


    @GetMapping("/add")
    public String addHomeworkForm(Model mode,
                                  HttpSession session) {

        return "homework-add";
    }

    @PostMapping("/add")
    public String addHomework(@Valid @ModelAttribute("homeworkBindingModel")HomeworkBindingModel bindingModel,
                              BindingResult result,
                              HttpSession session) {

        return "redirect:/homework-add";
    }
}

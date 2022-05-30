package vehicles.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import vehicles.service.BrandService;
import vehicles.web.bind.ModelBinding;


@Controller
@RequestMapping("/models")
public class ModelController {

    private final BrandService brandService;

    @Autowired
    public ModelController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/add")
    public ModelAndView add(ModelAndView view){
        view.addObject("brands", brandService.getBrands());
        view.setViewName("models-add");
        return view;
    }

    @PostMapping("/add")
    public String add(@ModelAttribute ModelBinding binding, BindingResult result){

        if (result.hasErrors()) {
            return "add";
        }

        brandService.addModel(binding);
        return "redirect:add";
    }
}

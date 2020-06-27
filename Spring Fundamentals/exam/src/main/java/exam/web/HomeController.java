package exam.web;

import exam.model.entity.CategoryType;
import exam.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;


@Controller
public class HomeController extends BaseController {

    private final ProductService productService;
    private final ModelMapper mapper;

    @Autowired
    public HomeController(ProductService productService, ModelMapper mapper) {
        this.productService = productService;
        this.mapper = mapper;
    }


    @GetMapping("/")
    public String index(HttpSession session){
        if(isAuthorizedUser(session)) {
            return "redirect:/home";
        }
        return "index";
    }

    @GetMapping("/home")
    public String home(Model model,
                       HttpSession session) {

        if(!isAuthorizedUser(session)) {
            return "redirect:/";
        }

        model.addAttribute("foods", productService.getProducts(CategoryType.Food));
        model.addAttribute("drinks", productService.getProducts(CategoryType.Drink));
        model.addAttribute("households", productService.getProducts(CategoryType.Household));
        model.addAttribute("products", productService.getProducts(CategoryType.Other));

        return "home";
    }
}

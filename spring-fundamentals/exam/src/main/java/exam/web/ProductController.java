package exam.web;

import exam.exception.AlreadyExistsException;
import exam.model.bind.ProductAddBindModel;
import exam.model.service.ProductServiceModel;
import exam.service.CategoryService;
import exam.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("products")
public class ProductController extends BaseController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final ModelMapper mapper;

    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService, ModelMapper mapper) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.mapper = mapper;
    }


    @GetMapping("/add")
    public String addProductForm(Model model) {

        if (!model.containsAttribute("productAddBindModel")) {
            model.addAttribute("productAddBindModel", new ProductAddBindModel());
            model.addAttribute("errors", false);
        }
        return "product-add";
    }

    @PostMapping("/add")
    public String addProduct(@Valid @ModelAttribute("productAddBindModel") ProductAddBindModel bindingModel,
                                  BindingResult result,
                                  RedirectAttributes attributes) {

        if (!result.hasErrors()) {
            try {
                ProductServiceModel productServiceModel = mapper.map(bindingModel, ProductServiceModel.class);
                productServiceModel.setCategory(categoryService.getByName(bindingModel.getCategory()));
                productService.addProduct(productServiceModel);

            } catch (AlreadyExistsException ex) {
                // service error
                result.rejectValue(ex.getField(), "error.productAddBindModel", ex.getMessage());
            }
        }

        if (result.hasErrors()) {
            attributes.addFlashAttribute("productAddBindModel", bindingModel);
            attributes.addFlashAttribute("org.springframework.validation.BindingResult.productAddBindModel", result);
            return "redirect:/products/add";
        }
        return "redirect:/home";
    }


    @RequestMapping("/buy/{id}")
    public String buy (Model model,
                    HttpSession session,
                    @PathVariable("id") String id) {

        if(!isAuthorizedUser(session)) {
            return "redirect:/";
        }

        productService.buyById(id);

        return "redirect:/home";
    }

    @GetMapping("/buyAll")
    public String buyAll (Model model,
                          HttpSession session) {

        if(!isAuthorizedUser(session)) {
            return "redirect:/";
        }

        productService.buyAll();

        return "redirect:/home";
    }
}

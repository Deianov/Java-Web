package vehicles.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vehicles.model.Brand;
import vehicles.service.BrandService;

@Controller
@RequestMapping("/brands")
public class BrandController {

    private final BrandService service;

    @Autowired
    public BrandController(BrandService service) {
        this.service = service;
    }

    @GetMapping("/add")
    public String add(){
        return "brand-add";
    }

    @PostMapping("/add")
    public String add(@RequestParam("name") String name){
        if (!service.existsByName(name)) {
            service.createBrand(new Brand(name));
        }
        return "redirect:add";
    }
}

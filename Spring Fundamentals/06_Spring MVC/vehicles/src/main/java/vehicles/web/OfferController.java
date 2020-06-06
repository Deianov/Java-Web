package vehicles.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import vehicles.model.Offer;
import vehicles.model.enums.EngineType;
import vehicles.service.BrandService;
import vehicles.service.OfferService;

@Controller
@RequestMapping("/offers")
public class OfferController {

    private final OfferService service;
    private final BrandService brandService;

    @Autowired
    public OfferController(OfferService service, BrandService brandService) {
        this.service = service;
        this.brandService = brandService;
    }

    @GetMapping("/add")
    public String getOfferForm(Model model) {
        model.addAttribute("offer", new Offer());
        model.addAttribute("brands", brandService.getBrands());
        model.addAttribute("engines", EngineType.values());
        return "offers/add";
    }

    @PostMapping("/add")
    public String createOffer(@RequestAttribute("offer")Offer offer){
        service.createOffer(offer);
        return "redirect:offers";
    }

    @GetMapping
    public String offers(){
        return "offers/all";
    }

    @GetMapping("/details")
    public String details(){
        return "offers/details";
    }
}

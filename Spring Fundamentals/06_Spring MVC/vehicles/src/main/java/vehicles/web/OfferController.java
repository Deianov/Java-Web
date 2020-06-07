package vehicles.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import vehicles.constant.Constant;
import vehicles.model.Offer;
import vehicles.model.enums.EngineType;
import vehicles.service.BrandService;
import vehicles.service.OfferService;

@Controller
@RequestMapping("/offers")
public class OfferController {
    private static final Logger LOGGER = LogManager.getLogger(OfferController.class.getName());

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
        return "offers-add";
    }

    @PostMapping("/add")
    public String createOffer(@ModelAttribute("offer")Offer offer, Errors errors){
        if(errors.hasErrors()) {
            LOGGER.error("Error creating offer: {}", errors.getAllErrors());
            return "offers-add";
        }
        try {
            // TODO: 07.06.2020 hard-code
            if(offer.getImageUrl() == null){
                offer.setImageUrl(Constant.CARS_HARDCODE_URL);
            }
            service.createOffer(offer);

        } catch(Exception ex) {
            LOGGER.error("Error creating offer", ex);
            return "offers-add";
        }
        return "redirect:/offers";
    }

    @GetMapping
    public String offers(Model model){
        model.addAttribute("offers", service.getOffers());
        return "offers";
    }

    @GetMapping("/details/{id}")
    public ModelAndView getById(ModelAndView modelAndView, @PathVariable Long id) {
        modelAndView.setViewName("details");
        modelAndView.addObject("offer", service.getOfferById(id));
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id") Long id) {
        service.deleteOffer(id);
        return "redirect:/offers";
    }
}

package vehicles.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vehicles.model.Offer;
import vehicles.service.BrandService;
import vehicles.service.OfferService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @GetMapping
    public String offers(Model model) {
        model.addAttribute("offers", service.getOffers());
        return "offers";
    }

    @GetMapping("/add")
    public String getOfferForm(Model model,
                               HttpSession session,
                               RedirectAttributes redirectAttributes) {
        if(session.getAttribute("user") == null) {
            redirectAttributes.addAttribute("redirectUrl", "/offers/add");
            return "redirect:/users/login";
        }
        if (!model.containsAttribute("offer")) {
            model.addAttribute("offer", new Offer());
        }
        // TODO: 15.06.2020 F5
        model.addAttribute("brands", brandService.getBrands());
        return "offers-add";
    }

    @PostMapping("/add")
    public String createOffer(@Valid @ModelAttribute("offer") Offer offer,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            LOGGER.error("Error creating offer: {}", bindingResult.getAllErrors());
            redirectAttributes.addFlashAttribute("offer", offer);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.offer", bindingResult);
            return "redirect:/offers/add";
        }
        try {
            service.createOffer(offer);

        } catch (Exception ex) {
            LOGGER.error("Error creating offer", ex);
            redirectAttributes.addFlashAttribute("offer", offer);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.offer", bindingResult);
            return "redirect:/offers/add";
        }
        return "redirect:/offers";
    }

    @GetMapping("/{id}")
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

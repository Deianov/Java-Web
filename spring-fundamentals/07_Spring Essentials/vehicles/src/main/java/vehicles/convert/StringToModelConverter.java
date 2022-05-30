package vehicles.convert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import vehicles.model.Model;
import vehicles.service.BrandService;


@Component
public class StringToModelConverter implements Converter<String, Model> {

    private final BrandService brandService;

    @Autowired
    public StringToModelConverter(BrandService brandService) {
        this.brandService = brandService;
    }

    @Override
    public Model convert(String from) {
        if(from != null && from.length() > 0) {
            Model model = brandService.getModelById(Long.parseLong(from));
            return model;
        }
        return null;
    }
}

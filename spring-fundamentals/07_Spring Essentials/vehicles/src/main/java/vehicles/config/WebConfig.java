package vehicles.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import vehicles.convert.StringToEnumConverterFactory;
import vehicles.convert.StringToModelConverter;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private StringToModelConverter stringToModelConverter;


    // TODO: 15.06.2020 ???
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:uploads/");
    }

    // add simple controller
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("redirect:/home");
        registry.addViewController("/home").setViewName("home");
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(stringToModelConverter);
        registry.addConverterFactory(new StringToEnumConverterFactory());
    }
}

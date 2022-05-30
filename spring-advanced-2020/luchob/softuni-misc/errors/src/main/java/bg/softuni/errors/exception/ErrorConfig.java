package bg.softuni.errors.exception;

import java.util.List;
import java.util.Map;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.error.ErrorAttributeOptions.Include;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.WebRequest;

@Configuration
public class ErrorConfig {

  //Uncomment
  //@Bean
  DefaultErrorAttributes errorAttributes() {
    return new DefaultErrorAttributes() {
      @Override
      public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        Map<String, Object> unfiltered = super.getErrorAttributes(webRequest, options.isIncluded(
            Include.STACK_TRACE));
        Map<String, Object> filtered = super.getErrorAttributes(webRequest, options);

        if (unfiltered.containsKey("message")) {
          filtered.put("message", unfiltered.get("message"));
        }
        filtered.put("softuni", true);
        return filtered;
      }
    };
  }

}

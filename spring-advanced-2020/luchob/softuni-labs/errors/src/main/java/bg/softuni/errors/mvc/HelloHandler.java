package bg.softuni.errors.mvc;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class HelloHandler {

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({HelloException.class})
  public ModelAndView handleHelloException(HelloException ex) {
    ModelAndView modelAndView = new ModelAndView("errorhandler");
    modelAndView.addObject("message", ex.getMessage());
    return modelAndView;
  }

}

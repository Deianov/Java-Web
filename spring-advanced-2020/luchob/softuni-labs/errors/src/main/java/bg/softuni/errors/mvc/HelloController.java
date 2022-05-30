package bg.softuni.errors.mvc;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

  @GetMapping("/crash-me")
  public ModelAndView crashMe() {
    throw new HelloException("I crashed!");
  }

  @GetMapping("/crash-me-bad")
  public ModelAndView crashMeBad() {
    throw new RuntimeException("I crashed badly");
  }

//  @ResponseStatus(HttpStatus.BAD_REQUEST)
//  @ExceptionHandler({HelloException.class})
//  public ModelAndView handleHelloException(HelloException ex) {
//    ModelAndView modelAndView = new ModelAndView("errorhandler");
//    modelAndView.addObject("message", ex.getMessage());
//    return modelAndView;
//  }

}

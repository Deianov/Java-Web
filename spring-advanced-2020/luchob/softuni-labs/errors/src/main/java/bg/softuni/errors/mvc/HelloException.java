package bg.softuni.errors.mvc;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class HelloException extends RuntimeException {

  public HelloException(String message) {
    super(message);
  }
}

package bg.softuni.events.custom;

import org.springframework.context.ApplicationEvent;

public class AddProductEvent extends ApplicationEvent {

  private final String message;

  public AddProductEvent(Object source, String message) {
    super(source);
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  @Override
  public String toString() {
    return "AddProductEvent{" +
        "message='" + message + '\'' +
        '}';
  }
}

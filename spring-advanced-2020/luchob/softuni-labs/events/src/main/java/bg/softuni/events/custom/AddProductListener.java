package bg.softuni.events.custom;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AddProductListener {

  @EventListener(AddProductEvent.class)
  public void onProductAdded(AddProductEvent productEvent) {
    //todo: notify the users
    //todo: count the products for the day
    //todo: ....

    System.out.println(productEvent);
  }
}

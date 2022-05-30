package bg.softuni.events.context;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ContextRefreshedEventListener {

  @EventListener
  public void onContextRefreshed(ContextRefreshedEvent event) {
    System.out.println("Context refreshed EventListener: " + event);
  }

}

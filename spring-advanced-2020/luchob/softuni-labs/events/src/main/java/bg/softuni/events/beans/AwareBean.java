package bg.softuni.events.beans;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

//@Component
public class AwareBean implements ApplicationContextAware {

  @Override
  public void setApplicationContext(ApplicationContext applicationContext)
      throws BeansException {
    System.out.println("I got the application context!");
  }
}

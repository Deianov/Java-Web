package bg.softuni.events.beans;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class PostProcessor implements BeanPostProcessor {

  public Object postProcessBeforeInitialization(Object bean, String beanName)
      throws BeansException {
    if (beanName.contains("annotationBean")) {
      System.out.println("AB: Before initialization: " + beanName);
    }

    return bean;
  }

  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    if (beanName.contains("annotationBean")) {
      System.out.println("AB: After initialization: " + beanName);
    }

    return bean;
  }

}

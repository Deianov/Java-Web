package bg.softuni.events.beans;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class PostProcessor implements BeanPostProcessor {

  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
    if ("annotationBean".equalsIgnoreCase(beanName)){
      System.out.println("Annotation bean before init");
    }

    return bean;
  }

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    if ("annotationBean".equalsIgnoreCase(beanName)){
      System.out.println("Annotation bean after init");
    }

    return bean;
  }
}

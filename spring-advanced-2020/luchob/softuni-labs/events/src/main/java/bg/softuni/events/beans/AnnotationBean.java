package bg.softuni.events.beans;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class AnnotationBean {

  @PostConstruct
  public void postConstruct() {
    System.out.println("AnnotationBean: Bean constructed!");
  }

  @PreDestroy
  public void preDestroy() {
    System.out.println("AnnotationBean: I'm dying!!!");
  }
}

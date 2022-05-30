package bg.softuni.events.beans;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class AnnotationBean {

  @PostConstruct
  public void postContruct() {
    System.out.println("AB: After construction...");
  }

  @PreDestroy
  public void preDestroy() {
    System.out.println("AB: Before destruction...");
  }
}

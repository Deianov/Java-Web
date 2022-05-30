package bg.softuni.events.beans;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class InitBean implements InitializingBean, DisposableBean {

  @Override
  public void afterPropertiesSet() throws Exception {
    System.out.println("AfterPropertiesSet");
  }

  @Override
  public void destroy() throws Exception {
    System.out.println("On destroy...");
  }
}

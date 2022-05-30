package bg.softuni.events.run;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

public class MySpringApplicationRunListener implements SpringApplicationRunListener {

  public MySpringApplicationRunListener(SpringApplication app, String args[]) {

  }

  @Override
  public void starting() {
    System.out.println("spring app: starting");
  }

  @Override
  public void environmentPrepared(ConfigurableEnvironment environment) {
    System.out.println("spring app: environmentPrepared");
  }

  @Override
  public void contextPrepared(ConfigurableApplicationContext context) {
    System.out.println("spring app: contextPrepared");
  }

  @Override
  public void contextLoaded(ConfigurableApplicationContext context) {
    System.out.println("spring app: contextLoaded");
  }

  @Override
  public void started(ConfigurableApplicationContext context) {
    System.out.println("spring app: started");
  }

  @Override
  public void running(ConfigurableApplicationContext context) {
    System.out.println("spring app: running");
  }

  @Override
  public void failed(ConfigurableApplicationContext context, Throwable exception) {
    System.out.println("spring app: failed");
  }
}

package bg.softuni.events.springlistener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

public class MySpringApplicationRunListener implements SpringApplicationRunListener {

  public MySpringApplicationRunListener(SpringApplication springApplication, String args[]) {

  }

  @Override
  public void starting() {
    System.out.println("SARL: starting");
  }

  @Override
  public void environmentPrepared(ConfigurableEnvironment environment) {
    System.out.println("SARL: environmentPrepared");
  }

  @Override
  public void contextPrepared(ConfigurableApplicationContext context) {
    System.out.println("SARL: contextPrepared");
  }

  @Override
  public void contextLoaded(ConfigurableApplicationContext context) {
    System.out.println("SARL: contextLoaded");
  }

  @Override
  public void started(ConfigurableApplicationContext context) {
    System.out.println("SARL: started");
  }

  @Override
  public void running(ConfigurableApplicationContext context) {
    System.out.println("SARL: running");
  }

  @Override
  public void failed(ConfigurableApplicationContext context, Throwable exception) {
    System.out.println("SARL: failed");
  }
}

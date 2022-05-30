package bg.softuni.events.scheduling;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MyScheduler {

  private int seconds = 0;

  //@Scheduled(fixedDelay = 1000, initialDelay = 5000)
  public void doSomeWork() {

    System.out.println("I'm starting on every second! " + (++seconds));
  }

  //@Scheduled(cron = "* 2 * * * ?")
  public void scheduledByCron() {

  }

}

package bg.softuni.events.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MyScheduler {

  //@Scheduled(fixedDelay = 1000, initialDelay = 5000)
  public void scheduleFixed() {
    System.out.println("Schedule fixed, every second.");
  }

  //@Scheduled(cron = "*/1 * * * * *")
  public void scheduledCron() {
    System.out.println("My cron, every second");
  }
}

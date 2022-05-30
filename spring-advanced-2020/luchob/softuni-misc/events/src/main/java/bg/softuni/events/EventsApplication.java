package bg.softuni.events;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationContextInitializedEvent;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableCaching
@EnableScheduling
@SpringBootApplication
public class EventsApplication {

	public static void main(String[] args) {
		//SpringApplication.run(EventsApplication.class, args);
		SpringApplication springApp = new SpringApplication
				(EventsApplication.class);
		springApp.addListeners((ApplicationContextInitializedEvent e) -> { 		System.out.println("App context init event"); });
		springApp.run(args);
	}

}

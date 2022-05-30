package bg.softunit.webflux.client;

import bg.softunit.webflux.client.client.ExchangeRateClient;
import bg.softunit.webflux.client.domain.ExchangeRate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

//@Component
public class WebfluxRateRunner implements CommandLineRunner {

  private final ExchangeRateClient client;

  public WebfluxRateRunner(ExchangeRateClient client) {
    this.client = client;
  }

  @Override
  public void run(String... args) {
    Flux<ExchangeRate> ratesFlux = client.getRateStream();

    ratesFlux.subscribe(System.out::println);
  }
}

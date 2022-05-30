package bg.softunit.webflux.client.service;

import bg.softunit.webflux.client.client.ExchangeRateClient;
import bg.softunit.webflux.client.domain.ExchangeRate;
import bg.softunit.webflux.client.repositories.ExchangeRateRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ExchangeRateService implements ApplicationListener<ContextRefreshedEvent> {

  private final ExchangeRateClient client;
  private final ExchangeRateRepository repository;

  public ExchangeRateService(ExchangeRateClient client,
      ExchangeRateRepository repository) {

    this.client = client;
    this.repository = repository;
  }

  @Override
  public void onApplicationEvent(ContextRefreshedEvent event) {
    client.
        getRateStream().
        subscribe(exchangeRate -> {
          Mono<ExchangeRate> exchangeRateMono = repository.save(exchangeRate);
          exchangeRateMono.subscribe(exRate -> System.out.println("Saved rate: " + exRate));
        });
  }
}

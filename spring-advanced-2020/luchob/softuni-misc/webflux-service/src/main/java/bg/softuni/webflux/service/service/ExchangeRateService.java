package bg.softuni.webflux.service.service;

import bg.softuni.webflux.service.model.ExchangeRate;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class ExchangeRateService {

  private static final Logger LOGGER = LoggerFactory.getLogger(ExchangeRateService.class);

  private List<ExchangeRate> exchangeRates = new ArrayList<>();

  public ExchangeRateService() {
    exchangeRates.add(new ExchangeRate("EUR", "BGN", Instant.now(), BigDecimal.valueOf(1.96)));
    exchangeRates.add(new ExchangeRate("USD", "BGN", Instant.now(), BigDecimal.valueOf(1.74)));
    exchangeRates.add(new ExchangeRate("GBP", "BGN", Instant.now(), BigDecimal.valueOf(2.16)));
    exchangeRates.add(new ExchangeRate("RSD", "BGN", Instant.now(), BigDecimal.valueOf(0.017)));
  }

  public Flux<ExchangeRate> getExchangeRateStream(int durationInterval) {

    // Generate stream of quotes
    Flux<ExchangeRate> ret = Flux.generate(() -> 0,
        (index, sink) -> {
          ExchangeRate updatedRate = randomize(exchangeRates.get(index));
          sink.next(updatedRate);
          LOGGER.info("Generated a new exchange rate in the stream...");
          return (++index) % exchangeRates.size();
        });

    if (durationInterval > 0) {
      return ret.delayElements(Duration.ofSeconds(durationInterval));
    } else {
      return ret;
    }
  }

  private ExchangeRate randomize(ExchangeRate initial) {
    return new ExchangeRate(
        initial.getFromCurrency(),
        initial.getToCurrency(),
        Instant.now(),
        randomize(initial.getRate()));
  }

  private BigDecimal randomize(BigDecimal initialRate) {
    Random random = new Random();
    double deviation = initialRate.doubleValue() *
        random.nextDouble() *
        0.1 * (random.nextDouble() > 0.5 ? -1 : 1);
    return BigDecimal.valueOf(initialRate.doubleValue() + deviation);
  }

}

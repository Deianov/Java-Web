package bg.softunit.webflux.client.client;

import bg.softunit.webflux.client.config.ClientConfig;
import bg.softunit.webflux.client.domain.ExchangeRate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Component
public class ExchangeRateClient {

  private static final Logger LOGGER = LoggerFactory.getLogger(ExchangeRateClient.class);

  private final String SERVICE_URL;
  private final String API_PATH = "/rates";

  public ExchangeRateClient(ClientConfig clientConfig) {

    SERVICE_URL = clientConfig.getSchema() + "://" + clientConfig.getHost() + ":" + clientConfig.getPort();

    LOGGER.debug("Service URL is {}" + SERVICE_URL);
  }

  public Flux<ExchangeRate> getRateStream(){

    return WebClient.builder()
        .baseUrl(SERVICE_URL)
        .build()
        .get()
        .uri(API_PATH)
        .accept(MediaType.APPLICATION_STREAM_JSON)
        .retrieve()
        .bodyToFlux(ExchangeRate.class);
  }

}

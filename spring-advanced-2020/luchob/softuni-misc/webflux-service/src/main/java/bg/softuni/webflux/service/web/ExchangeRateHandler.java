package bg.softuni.webflux.service.web;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import bg.softuni.webflux.service.model.ExchangeRate;
import bg.softuni.webflux.service.service.ExchangeRateService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class ExchangeRateHandler {

  private final ExchangeRateService exchangeRateService;

  public ExchangeRateHandler(ExchangeRateService exchangeRateService) {
    this.exchangeRateService = exchangeRateService;
  }

  public Mono<ServerResponse> getExchangeRates(ServerRequest request) {
    int size = Integer.parseInt(request.queryParam("size").orElse("10"));

    return ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(this.exchangeRateService.getExchangeRateStream(0)
            .take(size), ExchangeRate.class);
  }

  public Mono<ServerResponse> streamExchangeRates(){
    return ok().contentType(MediaType.APPLICATION_STREAM_JSON)
        .body(this.exchangeRateService.getExchangeRateStream(1),
            ExchangeRate.class);
  }

}

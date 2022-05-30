package bg.softuni.webflux.service.web;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@Configuration
public class ExchangeRateRouter {

  @Bean
  public RouterFunction<ServerResponse> route(ExchangeRateHandler handler){
    return RouterFunctions
        .route(GET("/rates").and(accept(MediaType.APPLICATION_JSON)), handler::getExchangeRates)
        .andRoute(GET("/rates").and(accept(MediaType.APPLICATION_STREAM_JSON)), sr -> handler.streamExchangeRates());
  }

}

package bg.softuni.fluxmono;

import java.util.function.Consumer;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class WebfluxGeneralTest {

  @Test
  public void fluxToStream() {
    Flux<String> springProjects = Flux.just(getSpringProjects());

    springProjects.
        toStream().
        forEach(System.out::println);
  }

  @Test
  public void subscribeToFlux() {
    Flux<String> springProjects = Flux.just(getSpringProjects());

    springProjects.
        subscribe(System.out::println);
  }

  @Test
  public void doOnEach() {
    Flux<String> springProjects = Flux.just(getSpringProjects());

    springProjects.
        doOnEach(s -> {
          if (s.isOnNext()) {
            System.out.println(s.get());
          }
        }).
        subscribe();
  }

  @Test
  public void mapAndFilter() {
    Flux<String> springProjects = Flux.just(getSpringProjects());

    springProjects.
        map(String::toUpperCase).
        filter(s -> s.contains("DATA")).
        subscribe(System.out::println);
  }

  @Test
  public void collector() {
    Flux<String> springProjects = Flux.just(getSpringProjects());

    springProjects.
        map(String::length).
        collect(Collectors.summingInt(Integer::intValue)).
        subscribe(System.out::println);
  }


  @Test
  public void subscribe() {
    Flux<String> springProjects = Flux.just(getSpringProjects());

    Consumer<String> nextConsumer = System.out::println;

    Consumer<Throwable> errorConsumer = Throwable::printStackTrace;

    Runnable onDone = () -> System.out.println("We are all done!");

    springProjects.subscribe(nextConsumer,
        errorConsumer,
        onDone);
  }

  @Test
  public void mono() {
    Mono.
        just("TEST").
        map(String::toLowerCase).
        subscribe(System.out::println);
  }

  private String[] getSpringProjects() {
    return new String[]{"Spring Batch",
        "Spring DATA",
        "Spring DATA Rest",
        "Spring Kafka",
        "Spring JMS",
        "Spring MVC"};
  }



}

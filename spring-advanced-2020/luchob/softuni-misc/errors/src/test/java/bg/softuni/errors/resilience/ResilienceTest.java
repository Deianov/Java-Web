package bg.softuni.errors.resilience;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig.SlidingWindowType;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import java.util.concurrent.Callable;
import java.util.function.Function;
import org.junit.jupiter.api.Test;

public class ResilienceTest {

  @Test
  public void testCircuitBreaker() {

    FailingService failingService = new FailingService();

    CircuitBreakerConfig config = CircuitBreakerConfig.custom()
        .failureRateThreshold(20)
        .slidingWindow(10, 5, SlidingWindowType.COUNT_BASED)
        .build();

    CircuitBreakerRegistry registry = CircuitBreakerRegistry.of(config);
    CircuitBreaker circuitBreaker = registry.circuitBreaker("my");
    Callable<String> failCall = CircuitBreaker
        .decorateCallable(circuitBreaker, failingService::fail);

    int calls = 0;
    for (int i = 0; i < 10; i++) {
      try {
        failCall.call();
      } catch (Exception e) {
        calls++;
      }
    }

    System.out.println(failingService.getNumberOfCalls());
  }

}

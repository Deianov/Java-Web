package bg.softuni.errors.resilience;

import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.stereotype.Service;

@Service
public class FailingService {

  private AtomicInteger numberOfCalls = new AtomicInteger(0);

  public String fail() {
    numberOfCalls.incrementAndGet();
    throw new RuntimeException("I'm designed to fail!");
  }

  public Integer getNumberOfCalls() {
    return numberOfCalls.intValue();
  }
}

package bg.softuni.reactive;

import static org.awaitility.Awaitility.await;

import java.util.List;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReactiveTests {
  @Test
  public void testAllItemsConsumed()
      throws InterruptedException {

    SubmissionPublisher<String> publisher = new SubmissionPublisher<>();
    SimpleSubscriber<String> mySubscriber = new SimpleSubscriber<>();
    publisher.subscribe(mySubscriber);

    Assertions.assertEquals(1, publisher.getNumberOfSubscribers());

    List<String> names = List.of("senko", "john", "anna", "peter");

    names.forEach(publisher::submit);
    publisher.close();

    await().
        atMost(1, TimeUnit.SECONDS)
        .until(
            () -> mySubscriber.getCountOfConsumedElements() == names.size()
        );
  }

  @Test
  public void testTransformation() {

    // given
    SubmissionPublisher<String> publisher = new SubmissionPublisher<>();
    TransformationProcessor<String, String> transformProcessor
        = new TransformationProcessor<>(String::toUpperCase);

    SimpleSubscriber<String> subscriber = new SimpleSubscriber<>();
    List<String> items = List.of("senKo", "Pesho", "Lilly");
    List<String> expectedResult = List.of("SENKO", "PESHO", "LILLY");

    // when
    publisher.subscribe(transformProcessor);
    transformProcessor.subscribe(subscriber);
    items.forEach(publisher::submit);
    publisher.close();

    // then
    await().atMost(1000, TimeUnit.MILLISECONDS)
        .until(() ->
            expectedResult.equals(subscriber.getConsumedElements())
        );
  }

}

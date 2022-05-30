package bg.softuni.reactive;

import java.util.concurrent.Flow;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.SubmissionPublisher;
import java.util.function.Function;

/**
 * A processor that applies a transformation function provided as an argument to
 * its constructor.
 * @param <T>
 * @param <R>
 */
public class TransformationProcessor<T, R> implements Flow.Processor<T, R> {

  private final SubmissionPublisher<R> publisher;
  private final Function<T, R> transformation;
  private Subscription subscription;

  public TransformationProcessor(Function<T, R> transformation){
    this.transformation = transformation;
    publisher = new SubmissionPublisher<R>();
  }

  @Override
  public void subscribe(Subscriber<? super R> subscriber) {
    publisher.subscribe(subscriber);
  }

  @Override
  public void onSubscribe(Subscription subscription) {

    this.subscription = subscription;
    this.subscription.request(1);
  }

  @Override
  public void onNext(T item) {
    R transformed = transformation.apply(item);
    publisher.submit(transformed);
    this.subscription.request(1);
  }

  @Override
  public void onError(Throwable throwable) {
    throwable.printStackTrace();
  }

  @Override
  public void onComplete() {
    System.out.println("The transformation is COMPLETE...");
  }
}

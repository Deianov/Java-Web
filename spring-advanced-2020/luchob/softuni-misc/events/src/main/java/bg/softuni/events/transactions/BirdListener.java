package bg.softuni.events.transactions;

import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class BirdListener {

  @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
  public void handleCustom(BirdEvent event) {
    System.out.println("Handling event inside a transaction. Bird " + event.getSource());
  }

}

package bg.softuni.events.transactions;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BirdController {

  private final BirdRepository birdRepository;
  private final ApplicationEventPublisher eventPublisher;

  public BirdController(BirdRepository birdRepository,
      ApplicationEventPublisher eventPublisher) {
    this.birdRepository = birdRepository;
    this.eventPublisher = eventPublisher;
  }

  @Transactional
  @PostMapping("/birds")
  public ResponseEntity<Bird> createBird(@RequestBody Bird bird) {
    Bird newBird = birdRepository.save(bird);
    eventPublisher.publishEvent(new BirdEvent(newBird.getBreed()));
    return ResponseEntity.status(HttpStatus.CREATED).body(newBird);
  }

  @GetMapping("/birds")
  public ResponseEntity<List<Bird>> createBird() {
    eventPublisher.publishEvent(new BirdEvent("Unknown"));
    return ResponseEntity.ok(birdRepository.findAll());
  }
}

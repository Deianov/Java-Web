package bg.softuni.kotlindemo

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.time.Instant

@RestController
class HelloMessageController {

  private val messages = listOf("Hello!", "Good morning!", "How are you?")
  private val senders = listOf("Peter", "George")

  @GetMapping("/message")
  fun getHelloMessage() : ResponseEntity<HelloMessage> {
    return ResponseEntity.ok(generateHelloMessage());
  }

  private fun generateHelloMessage() : HelloMessage {
    return HelloMessage(
        message=messages.random(),
        sender = senders.random(),
        generated = Instant.now())
  }
}

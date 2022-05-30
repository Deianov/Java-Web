package bg.softuni.kotlin

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.time.Instant

@RestController
class HomeController {

  private val message = listOf("Good morning", "Gute nacht", "Чао")
  private val sender = listOf("John", "Hans", "Pesho")

  @GetMapping
  fun hello() : HelloMessage {
    return HelloMessage(
      messge = message.random(),
        sender = sender.random(),
        generated = Instant.now()
    )
  }

}

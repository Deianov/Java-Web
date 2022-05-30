package bg.softuni.kotlin

import java.time.Instant

data class HelloMessage(
    val messge: String,
    val sender: String,
    val generated: Instant
)

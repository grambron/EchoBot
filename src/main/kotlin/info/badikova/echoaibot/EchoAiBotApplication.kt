package info.badikova.echoaibot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class EchoAiBotApplication

fun main(args: Array<String>) {
    runApplication<EchoAiBotApplication>(*args)
}

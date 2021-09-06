package info.badikova.echoaibot.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebConfig {

    @Bean("webClient")
    fun webClient(): WebClient {
        return WebClient.create()
    }

}
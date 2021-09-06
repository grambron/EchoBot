package info.badikova.echoaibot.vk.impl

import info.badikova.echoaibot.exception.VkApiException
import info.badikova.echoaibot.vk.MessageInfo
import info.badikova.echoaibot.vk.VkApiService
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import org.springframework.web.reactive.function.client.awaitExchange
import kotlin.random.Random

@Service
class VkApiServiceImpl(
    val webClient: WebClient,
    @Value("\${vk.api.url}") val vkApiUrl: String,
    @Value("\${vk.api.version}") val vkApiVersion: String,
    @Value("\${vk.token}") val token: String,
) : VkApiService {

    private val random = Random(System.currentTimeMillis())

    override suspend fun sendTextMessage(info: MessageInfo) {
        val url = makeVkUrl(
            vkApiUrl, "messages.send", mapOf(
                "random_id" to random.nextInt(),
                "peer_id" to info.peerId,
                "message" to info.text,
                "access_token" to token,
                "v" to vkApiVersion
            )
        )
        webClient.get().uri(url).awaitExchange { clientResponse ->
            if (!clientResponse.statusCode().is2xxSuccessful) {
                val body = clientResponse.awaitBody<String>()
                throw VkApiException("Status is not 200: $body")
            }
        }
    }

}
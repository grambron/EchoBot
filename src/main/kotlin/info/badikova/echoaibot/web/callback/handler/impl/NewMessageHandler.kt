package info.badikova.echoaibot.web.callback.handler.impl

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.treeToValue
import info.badikova.echoaibot.core.bot.EchoBot
import info.badikova.echoaibot.core.bot.EchoInfo
import info.badikova.echoaibot.exception.DeserializationException
import info.badikova.echoaibot.web.callback.handler.CallbackMessage
import info.badikova.echoaibot.web.callback.handler.CallbackMessageHandler
import org.springframework.stereotype.Component

@Component
class NewMessageHandler(
    val bot: EchoBot
) : CallbackMessageHandler {

    override val messageType: String = "message_new"

    private val objectMapper = jacksonObjectMapper()

    override suspend fun process(message: CallbackMessage) {
        val wrapper = deserialize(message)
        bot.onMessageReceived(MessageToEchoInfoAdapter(wrapper))
    }

    private fun deserialize(message: CallbackMessage): MessageWrapper {
        return objectMapper.runCatching {
            treeToValue<MessageWrapper>(message.data)
                ?: throw IllegalStateException("Can not deserialize ${message.data}")
        }.getOrElse {
            throw DeserializationException(it)
        }
    }


    @JsonIgnoreProperties(ignoreUnknown = true)
    private class MessageWrapper(
        val message: Message
    )

    @JsonIgnoreProperties(ignoreUnknown = true)
    private class Message(
        @JsonProperty("peer_id")
        val peerId: Long,
        val text: String
    )

    private class MessageToEchoInfoAdapter(messageWrapper: MessageWrapper) : EchoInfo {
        override val peerId: Long = messageWrapper.message.peerId
        override val text: String = messageWrapper.message.text
    }

}
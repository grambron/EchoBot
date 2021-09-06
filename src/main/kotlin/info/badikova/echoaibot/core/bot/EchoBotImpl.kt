package info.badikova.echoaibot.core.bot

import info.badikova.echoaibot.vk.MessageInfo
import info.badikova.echoaibot.vk.VkApiService
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class EchoBotImpl(
    @Value("\${bot.name}") val botName: String,
    val vkApiService: VkApiService
) : EchoBot {


    override suspend fun onMessageReceived(echoInfo: EchoInfo) {
        val rawTextMessage = echoInfo.text
        if (!rawTextMessage.contains(botName)) return

        var message = rawTextMessage.replace("[$botName]", "")
        if (message.isEmpty()) return

        message = "Вы сказали: $message"
        val messageInfo = EchoIntoToMessageAdapter(echoInfo.peerId, message)
        vkApiService.sendTextMessage(messageInfo)
    }

    private class EchoIntoToMessageAdapter(
        override val peerId: Long,
        override val text: String
    ) : MessageInfo

}


package info.badikova.echoaibot.vk

interface VkApiService {

    suspend fun sendTextMessage(info: MessageInfo)

}
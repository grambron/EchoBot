package info.badikova.echoaibot.core.bot

interface EchoBot {

    suspend fun onMessageReceived(echoInfo: EchoInfo)

}
package info.badikova.echoaibot.web.callback.handler

interface CallbackHandleEntryPoint {

    fun register(callbackMessageHandler: CallbackMessageHandler)

    suspend fun processMessage(message: CallbackMessage)

}
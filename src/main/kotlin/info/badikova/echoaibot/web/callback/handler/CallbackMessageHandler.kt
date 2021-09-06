package info.badikova.echoaibot.web.callback.handler

import org.springframework.beans.factory.annotation.Autowired

interface CallbackMessageHandler {

    val messageType: String

    suspend fun process(message: CallbackMessage)

    @Autowired
    fun register(entryPoint: CallbackHandleEntryPoint) {
        entryPoint.register(this)
    }

}
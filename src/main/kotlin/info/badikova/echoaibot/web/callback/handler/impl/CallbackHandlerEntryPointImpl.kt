package info.badikova.echoaibot.web.callback.handler.impl

import info.badikova.echoaibot.web.callback.handler.CallbackHandleEntryPoint
import info.badikova.echoaibot.web.callback.handler.CallbackMessage
import info.badikova.echoaibot.web.callback.handler.CallbackMessageHandler
import org.springframework.stereotype.Component

@Component
class CallbackHandlerEntryPointImpl: CallbackHandleEntryPoint {

    private val handlers: MutableMap<String, CallbackMessageHandler> = mutableMapOf()

    override suspend fun processMessage(message: CallbackMessage) {
        val handler = handlers[message.type] ?: throw UnsupportedOperationException("Type not supported: ${message.type}")
        handler.process(message)
    }

    override fun register(callbackMessageHandler: CallbackMessageHandler) {
        handlers[callbackMessageHandler.messageType] = callbackMessageHandler
    }

}
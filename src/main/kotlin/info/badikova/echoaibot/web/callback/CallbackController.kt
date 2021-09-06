package info.badikova.echoaibot.web.callback

import info.badikova.echoaibot.web.callback.handler.CallbackHandleEntryPoint
import info.badikova.echoaibot.web.callback.handler.CallbackMessage
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*


@RestController
class CallbackController(
    @Value("\${vk.callback.response}") val callbackResponse: String,
    val callbackHandleEntryPoint: CallbackHandleEntryPoint
) {


    @PostMapping("\${vk.callback.url}")
    @ResponseStatus(HttpStatus.OK)
    suspend fun processEvent(@RequestBody message: CallbackMessage): String {
        callbackHandleEntryPoint.processMessage(message)
        return callbackResponse
    }

}
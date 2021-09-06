package info.badikova.echoaibot.exception.handler

import info.badikova.echoaibot.exception.DeserializationException
import info.badikova.echoaibot.exception.VkApiException
import info.badikova.echoaibot.util.LoggerDelegate
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalControllerAdvisor(
    @Value("\${vk.callback.response}") val vkApiCallbackResponse: String
) {

    private val logger by LoggerDelegate()

    @ExceptionHandler(UnsupportedOperationException::class)
    @ResponseStatus(HttpStatus.OK)
    fun handleUnsupportedEventType(exception: UnsupportedOperationException): String {
        logger.warn("Unsupported event type", exception)
        return vkApiCallbackResponse
    }

    @ExceptionHandler(VkApiException::class)
    @ResponseStatus(HttpStatus.OK)
    fun handleVkApiException(exception: VkApiException): String {
        logger.error("Exception in vk api", exception)
        return vkApiCallbackResponse
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.OK)
    fun handleAnyException(exception: Exception): String {
        logger.error("Unknown exception", exception)
        return vkApiCallbackResponse
    }

    @ExceptionHandler(DeserializationException::class)
    @ResponseStatus(HttpStatus.OK)
    fun handleDeserializationException(exception: DeserializationException): String {
        logger.error("Deserialization exception", exception)
        return vkApiCallbackResponse
    }

}
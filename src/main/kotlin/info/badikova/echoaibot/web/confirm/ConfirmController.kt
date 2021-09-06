package info.badikova.echoaibot.web.confirm

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class ConfirmController(
    @Value("\${vk.confirmationCode}") val confirmationString: String
) {

    @PostMapping("\${vk.confirm.url}")
    @ResponseStatus(HttpStatus.OK)
    fun confirmAddress(@RequestBody body: ConfirmDTO): String {
        return confirmationString
    }

}
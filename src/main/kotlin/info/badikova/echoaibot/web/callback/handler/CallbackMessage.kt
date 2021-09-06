package info.badikova.echoaibot.web.callback.handler

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.JsonNode

@JsonIgnoreProperties(ignoreUnknown = true)
data class CallbackMessage(
    val type: String,
    @JsonProperty("object")
    val data: JsonNode
)
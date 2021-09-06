package info.badikova.echoaibot.vk.impl

fun makeVkUrl(
    baseUrl: String,
    methodName: String,
    arguments: Map<String, Any>
): String {
    val params = if (arguments.isNotEmpty()) {
        "?" + arguments.entries.joinToString("&") { (key, value) -> "$key=$value" }
    } else ""
    return "$baseUrl/method/$methodName$params"
}
package util

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonLiteral
import kotlinx.serialization.json.JsonObject
import wiki_media.error.WikiError

fun JsonObject.getJsonLiteralContentOrThrow(key: String): String{
    val jsonLiteral = this[key] as? JsonLiteral
        ?: throw WikiError.WikiResponseMissingKey.missingKey(key)

    return jsonLiteral.content
}

fun JsonObject.getJsonObjectOrThrow(key: String): JsonObject{
    return this[key] as? JsonObject
        ?: throw WikiError.WikiResponseMissingKey.missingKey("query")
}
fun JsonObject.getJsonArrayOrThrow(key: String): JsonArray{
    return this[key] as? JsonArray
        ?: throw WikiError.WikiResponseMissingKey.missingKey("query")
}
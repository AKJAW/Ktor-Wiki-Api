package wiki_media.request.parser

import kotlinx.serialization.json.JsonObject

interface WikiMediaParser<I, R> {
    fun parse(content: I): R
}
package wiki_media.request.api

import kotlinx.serialization.json.JsonObject

interface ApiCaller {
    suspend fun call(url: String): JsonObject
}

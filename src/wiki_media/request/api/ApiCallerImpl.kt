package wiki_media.request.api

import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get
import kotlinx.serialization.json.JsonObject

class ApiCallerImpl: ApiCaller<JsonObject>{
    override suspend fun call(url: String): JsonObject {
        val client = HttpClient {
            install(JsonFeature) {
                serializer = KotlinxSerializer()
            }
        }
        return client.get(url)
    }
}

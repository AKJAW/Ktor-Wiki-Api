package wiki_media.request.api

interface ApiCaller <T> {
    suspend fun call(url: String): T
}

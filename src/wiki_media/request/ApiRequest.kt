package wiki_media.request

interface ApiRequest <T> {
    suspend fun makeRequest(): T
}
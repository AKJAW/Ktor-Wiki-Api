package wiki_media.request.url

interface ApiUrlProvider {
    fun create(language: String): String
}
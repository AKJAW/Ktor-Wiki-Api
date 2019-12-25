package wiki_media.url

interface WikiMediaUrlProvider {
    fun create(language: String): String
}
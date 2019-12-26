package wiki_media.request.url

class RandomArticleApiUrlProvider(private val language: String): ApiUrlProvider {
    override fun create(): String {
        return ApiQueryUrlBuilder(language)
            .addParameter("format", "json")
            .addParameter("action", "query")
            .addParameter("generator", "random")
            .addParameter("grnnamespace", "0")
            .addParameter("prop", "pageimages|info|extracts")
            .addParameter("exlimit", "1")//TODO?
            .addParameter("explaintext")
            .addParameter("grnlimit", "1")
            .addParameter("inprop", "url")
            .addParameter("piprop", "original")
            .build()
    }
}
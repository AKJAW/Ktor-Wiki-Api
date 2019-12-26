package wiki_media.request.url

class ArticleFromPageIdUrlProvider(private val language: String, private val pageId: String): ApiUrlProvider {
    override fun create(): String {
        return ApiQueryUrlBuilder(language)
            .addParameter("format", "json")
            .addParameter("action", "query")
            .addParameter("prop", "pageimages|info|extracts")
            .addParameter("exlimit", "1")
            .addParameter("explaintext")
            .addParameter("inprop", "url")
            .addParameter("piprop", "original")
            .addParameter("pageids", pageId)
            .build()
    }
}
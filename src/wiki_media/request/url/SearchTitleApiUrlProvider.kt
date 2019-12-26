package wiki_media.request.url

class SearchTitleApiUrlProvider(private val language: String, private val title: String): ApiUrlProvider {
    override fun create(): String {
        return ApiQueryUrlBuilder(language)
            .addParameter("format", "json")
            .addParameter("action", "query")
            .addParameter("srlimit", "1")
            .addParameter("list", "search")
            .addParameter("srsearch", title)
            .build()
    }
}
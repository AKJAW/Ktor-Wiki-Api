package wiki_media.request.url

class RandomArticleApiUrlProvider: ApiUrlProvider {
    override fun create(language: String): String {
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
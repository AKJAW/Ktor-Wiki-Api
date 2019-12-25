package wiki_media.url

class RandomArticleUrlProvider(): WikiMediaUrlProvider {
    override fun create(language: String): String {
        return WikiMediaApiUrlBuilder(language)
            .addParameter("format", "json")
            .addParameter("action", "query")
            .addParameter("generator", "random")
            .addParameter("grnnamespace", "0")
            .addParameter("prop", "pageimages|info|extracts")
            .addParameter("explaintext")
            .addParameter("grnlimit", "1")
            .addParameter("inprop", "url")
            .addParameter("piprop", "original")
            .build()
    }
}
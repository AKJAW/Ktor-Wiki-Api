package wiki_media.data

data class WikiApiArticle(
    val article: ApiArticleResponse,
    val titles: List<String>
)
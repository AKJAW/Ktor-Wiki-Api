package wiki_media.data

data class ApiArticleResponse(
    val pageId: String = "",
    val name: String = "",
    val description: String = "",
    val image: String? = null,
    val url: String = ""
)
package wiki_media.url

internal class WikiMediaApiUrlBuilder(language: String) {
    private val parameters: MutableList<QueryParameter> = mutableListOf()
    private val baseUrl = "https://$language.wikipedia.org/w/api.php"

    fun addParameter(key: String, value: String? = null): WikiMediaApiUrlBuilder {
        val queryParameter =
            QueryParameter(key, value)
        parameters.add(queryParameter)
        return this
    }

    fun build(): String {
        return baseUrl + "?" + parameters.joinToString("&")
    }

    private data class QueryParameter(val key: String, val value: String?){
        override fun toString(): String {
            return if(value == null){
                key
            } else {
                "$key=$value"
            }
        }
    }
}

fun main() {
    val url = WikiMediaApiUrlBuilder("en")
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
    println(url)
}
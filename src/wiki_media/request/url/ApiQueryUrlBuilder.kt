package wiki_media.request.url

internal class ApiQueryUrlBuilder(language: String) {
    private val parameters: MutableList<QueryParameter> = mutableListOf()
    private val baseUrl = "https://$language.wikipedia.org/w/api.php"

    fun addParameter(key: String, value: String? = null): ApiQueryUrlBuilder {
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

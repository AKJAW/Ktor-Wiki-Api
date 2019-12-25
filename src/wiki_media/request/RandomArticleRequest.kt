package wiki_media.request


import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get
import wiki_media.error.WikiError
import wiki_media.language.LanguageTransformer
import wiki_media.url.WikiMediaUrlProvider
import io.ktor.http.Parameters
import kotlinx.serialization.json.JsonObject
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.qualifier.named
import wiki_media.data.ArticleResponse
import wiki_media.request.api.ApiCaller
import wiki_media.request.parser.WikiMediaParser

class RandomArticleRequest(queryParameters: Parameters): WikiMediaRequest, KoinComponent{
    private val languageTransformer: LanguageTransformer by inject()
    private val urlProvider: WikiMediaUrlProvider by inject(named("RandomArticle"))
    private val apiCaller: ApiCaller by inject()
    private val parser: WikiMediaParser<JsonObject, ArticleResponse> by inject()
    private val url: String

    init {
        val language = queryParameters["language"] ?: throw WikiError.LanguageMissingError()
        val correctLanguage = languageTransformer.transform(language)
        url = urlProvider.create(correctLanguage)
        println(url)
    }

    override suspend fun makeRequest(): ArticleResponse {

        val response: JsonObject = apiCaller.call(url)
        val articleResponse: ArticleResponse = parser.parse(response)
        println(articleResponse)
        return articleResponse
    }

}

data class Query(val pages: Any)
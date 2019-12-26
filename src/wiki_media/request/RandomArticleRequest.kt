package wiki_media.request

import wiki_media.error.WikiError
import wiki_media.language.LanguageTransformer
import wiki_media.request.url.ApiUrlProvider
import io.ktor.http.Parameters
import kotlinx.serialization.json.JsonObject
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.qualifier.named
import wiki_media.data.ArticleResponse
import wiki_media.request.api.ApiCaller
import wiki_media.request.parser.ResponseParser

class RandomArticleRequest(queryParameters: Parameters): ApiRequest, KoinComponent{
    private val languageTransformer: LanguageTransformer by inject()
    private val urlProvider: ApiUrlProvider by inject(named("RandomArticleUrlProvider"))
    private val apiCaller: ApiCaller<JsonObject> by inject(named("ApiCaller"))
    private val parser: ResponseParser<JsonObject, ArticleResponse> by inject(named("RandomArticleResponseParser"))
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

        return articleResponse
    }

}
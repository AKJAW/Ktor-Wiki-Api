package wiki_media.request

import wiki_media.error.WikiError
import wiki_media.language.LanguageTransformer
import wiki_media.request.url.ApiUrlProvider
import io.ktor.http.Parameters
import kotlinx.serialization.json.JsonObject
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.qualifier.named
import wiki_media.data.ApiArticleResponse
import wiki_media.data.WikiApiArticle
import wiki_media.request.api.ApiCaller
import wiki_media.request.parser.ResponseParser
import wiki_media.request.scraper.Scraper

class RandomArticleRequest(queryParameters: Parameters): ApiRequest, KoinComponent{
    private val languageTransformer: LanguageTransformer by inject()
    private val urlProvider: ApiUrlProvider by inject(named("RandomArticleUrlProvider"))
    private val apiCaller: ApiCaller<JsonObject> by inject(named("ApiCaller"))
    private val parser: ResponseParser<JsonObject, ApiArticleResponse> by inject(named("RandomArticleResponseParser"))
    private val scraper: Scraper<List<String>> by inject()
    private val url: String

    init {
        val language = queryParameters["language"] ?: throw WikiError.LanguageMissingError()
        val correctLanguage = languageTransformer.transform(language)
        url = urlProvider.create(correctLanguage)
        println(url)
    }

    override suspend fun makeRequest(): WikiApiArticle {
        val response: JsonObject = apiCaller.call(url)
        val apiArticleResponse: ApiArticleResponse = parser.parse(response)
        val titles = scraper.scrape(apiArticleResponse.url)
        print(titles)
        return WikiApiArticle(apiArticleResponse, titles)
    }

}
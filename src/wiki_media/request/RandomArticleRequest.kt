package wiki_media.request


import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.features.json.JacksonSerializer
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.request.get
import io.ktor.client.response.HttpResponse
import io.ktor.client.response.readText
import wiki_media.error.WikiError
import wiki_media.language.LanguageTransformer
import wiki_media.url.WikiMediaUrlProvider
import io.ktor.http.Parameters
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.qualifier.named

class RandomArticleRequest(queryParameters: Parameters): WikiMediaRequest, KoinComponent{
    private val languageTransformer: LanguageTransformer by inject()
    private val randomArticleUrlProvider: WikiMediaUrlProvider by inject(named("RandomArticle"))
    private val url: String

    init {
        val language = queryParameters["language"] ?: throw WikiError.LanguageMissingError()
        val correctLanguage = languageTransformer.transform(language)
        url = randomArticleUrlProvider.create(correctLanguage)
        println(url)
    }

    override suspend fun makeRequest(): String {
        val client = HttpClient {
            install(JsonFeature) {
                serializer = JacksonSerializer()
            }
        }
        val content = client.get<HttpResponse>(url)
        println(content.readText())
        return "s"
    }

}
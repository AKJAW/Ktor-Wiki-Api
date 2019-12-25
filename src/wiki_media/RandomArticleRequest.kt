package wiki_media

import wiki_media.errors.WikiError
import wiki_media.language.LanguageTransformer
import wiki_media.url.WikiMediaUrlProvider
import io.ktor.http.Parameters
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.qualifier.named

class RandomArticleRequest(queryParameters: Parameters): KoinComponent{
    private val languageTransformer: LanguageTransformer by inject()
    private val randomArticleUrlProvider: WikiMediaUrlProvider by inject(named("RandomArticle"))
    private val url: String

    init {
        val language = queryParameters["language"] ?: throw WikiError.LanguageMissingError()
        val correctLanguage = languageTransformer.transform(language)
        url = randomArticleUrlProvider.create(correctLanguage)
        println(url)
    }

}
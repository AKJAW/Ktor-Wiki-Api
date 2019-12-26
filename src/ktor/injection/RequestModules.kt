package ktor.injection

import kotlinx.serialization.json.JsonObject
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module
import wiki_media.data.ArticleResponse
import wiki_media.language.LanguageTransformer
import wiki_media.language.LanguageValidator
import wiki_media.language.WikiMediaLanguageTransformer
import wiki_media.request.api.ApiCaller
import wiki_media.request.api.ApiCallerImpl
import wiki_media.request.parser.ArticleParser
import wiki_media.request.parser.ResponseParser
import wiki_media.request.url.ApiUrlProvider
import wiki_media.request.url.RandomArticleApiUrlProvider

private val validLanguages = listOf("pl", "en")
private val languageModule = module {
    single<LanguageTransformer> { WikiMediaLanguageTransformer(get()) }
    single { LanguageValidator(validLanguages) }
}

private val urlProviderModule = module {
    single(named("RandomArticle")) { RandomArticleApiUrlProvider() } bind ApiUrlProvider::class
}

//Should be single?
private val apiModule = module {
    single { ApiCallerImpl() } bind ApiCaller::class
}

private val parserModule = module {
    single { ArticleParser() as ResponseParser<JsonObject, ArticleResponse> }
}

val requestModules = listOf(
    languageModule,
    urlProviderModule,
    apiModule,
    parserModule
)
package ktor.injection

import kotlinx.serialization.json.JsonObject
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module
import wiki_media.data.ApiArticleResponse
import wiki_media.language.LanguageTransformer
import wiki_media.language.LanguageValidator
import wiki_media.language.WikiMediaLanguageTransformer
import wiki_media.request.api.ApiCaller
import wiki_media.request.api.ApiCallerImpl
import wiki_media.request.parser.ArticleParser
import wiki_media.request.parser.ResponseParser
import wiki_media.request.scraper.ArticleLinkScraper
import wiki_media.request.scraper.Scraper
import wiki_media.request.url.ApiUrlProvider
import wiki_media.request.url.RandomArticleApiUrlProvider
import wiki_media.request.url.SearchTitleApiUrlProvider

private val validLanguages = listOf("pl", "en")
private val languageModule = module {
    single<LanguageTransformer> { WikiMediaLanguageTransformer(get()) }
    single { LanguageValidator(validLanguages) }
}

private val urlProviderModule = module {
    factory(named("RandomArticleUrlProvider")) {
        RandomArticleApiUrlProvider(it[0])
    } bind ApiUrlProvider::class
}

private val apiModule = module {
    single(named("ApiCaller")) { ApiCallerImpl() } bind ApiCaller::class
}

private val parserModule = module {
    single(named("RandomArticleResponseParser")) { ArticleParser() as ResponseParser<JsonObject, ApiArticleResponse> }
}

private val scraperModule = module {
    single { ArticleLinkScraper() as Scraper<List<String>> }
}

val requestModules = listOf(
    languageModule,
    urlProviderModule,
    apiModule,
    parserModule,
    scraperModule
)
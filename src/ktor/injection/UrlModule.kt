package ktor.injection

import wiki_media.url.RandomArticleUrlProvider
import wiki_media.url.WikiMediaUrlProvider
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

val urlProviderModule = module {
    single(named("RandomArticle")) { RandomArticleUrlProvider() } bind WikiMediaUrlProvider::class
}
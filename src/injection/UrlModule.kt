package com.akjaw.injection

import com.akjaw.wiki_media.language.LanguageTransformer
import com.akjaw.wiki_media.language.LanguageValidator
import com.akjaw.wiki_media.language.WikiMediaLanguageTransformer
import com.akjaw.wiki_media.url.RandomArticleUrlProvider
import com.akjaw.wiki_media.url.WikiMediaUrlProvider
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

val urlProviderModule = module {
    single(named("RandomArticle")) { RandomArticleUrlProvider() } bind WikiMediaUrlProvider::class
}
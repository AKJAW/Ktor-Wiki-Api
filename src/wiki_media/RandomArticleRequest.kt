package com.akjaw.wiki_media

import com.akjaw.errors.WikiError
import com.akjaw.wiki_media.language.LanguageTransformer
import com.akjaw.wiki_media.url.WikiMediaApiUrlBuilder
import com.akjaw.wiki_media.url.WikiMediaUrlProvider
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
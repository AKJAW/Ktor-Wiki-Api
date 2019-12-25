package com.akjaw.routes.random_article

import com.akjaw.errors.WikiError
import com.akjaw.wiki_media.WikiMediaApiUrl
import com.akjaw.wiki_media.language.LanguageTransformer
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import org.koin.ktor.ext.inject

fun Routing.randomArticle(){
    val languageTransformer: LanguageTransformer by inject()

    get("/random-article") {
        val language: String = call.request.queryParameters["language"] ?: throw WikiError.LanguageMissingError()

        val correctLanguage = languageTransformer.transform(language)

        val url = WikiMediaApiUrl.createBaseUrl(correctLanguage)

        call.respond(mapOf("OK" to true))
    }
}
package com.akjaw.routes.random_article

import com.akjaw.errors.WikiError
import com.akjaw.wiki_media.RandomArticleRequest
import com.akjaw.wiki_media.language.LanguageTransformer
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import org.koin.ktor.ext.inject

fun Routing.randomArticle(){
    get("/random-article") {
        val randomArticleRequest = RandomArticleRequest(call.request.queryParameters)

        call.respond(mapOf("OK" to true))
    }
}
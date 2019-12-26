package ktor.routing

import wiki_media.request.ArticleContentRequest
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import org.koin.ktor.ext.inject
import wiki_media.error.WikiError
import wiki_media.language.LanguageTransformer
import wiki_media.request.url.RandomArticleApiUrlProvider

fun Routing.randomArticle(){
    val languageTransformer: LanguageTransformer by inject()

    get("/random-article") {
        val languageParameter = call.request.queryParameters["language"] ?: throw WikiError.LanguageMissingError()
        val language = languageTransformer.transform(languageParameter)

        val provider = RandomArticleApiUrlProvider(language)
        val randomArticleRequest = ArticleContentRequest(provider)

        call.respond(randomArticleRequest.makeRequest())
    }
}
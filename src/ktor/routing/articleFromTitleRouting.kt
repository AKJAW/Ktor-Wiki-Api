package ktor.routing

import wiki_media.request.RandomArticleRequest
import io.ktor.application.call
import io.ktor.http.Parameters
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import org.koin.core.inject
import org.koin.ktor.ext.inject
import wiki_media.error.WikiError
import wiki_media.language.LanguageTransformer
import wiki_media.request.SearchTitleRequest

fun Routing.articleFromTitle(){
    val languageTransformer: LanguageTransformer by inject()

    get("/article-from-title") {
        val languageParameter = call.request.queryParameters["language"] ?: throw WikiError.LanguageMissingError()
        val language = languageTransformer.transform(languageParameter)
        val title = call.request.queryParameters["title"] ?: throw WikiError.TitleMissingError()

        val randomArticleRequest = SearchTitleRequest(language, title)
        //Set header to json
        call.respond(randomArticleRequest.makeRequest())
    }
}
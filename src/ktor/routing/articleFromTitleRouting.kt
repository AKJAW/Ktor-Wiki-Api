package ktor.routing

import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get
import org.koin.ktor.ext.inject
import wiki_media.error.WikiError
import wiki_media.language.LanguageTransformer
import wiki_media.request.ArticleContentRequest
import wiki_media.request.SearchTitleRequest
import wiki_media.request.url.ArticleFromPageIdUrlProvider
import wiki_media.request.url.SearchTitleApiUrlProvider

fun Routing.articleFromTitle(){
    val languageTransformer: LanguageTransformer by inject()

    get("/article-from-title") {
        val languageParameter = call.request.queryParameters["language"] ?: throw WikiError.LanguageMissingError()
        val language = languageTransformer.transform(languageParameter)
        val title = call.request.queryParameters["title"] ?: throw WikiError.TitleMissingError()

        val searchUrlProvider = SearchTitleApiUrlProvider(language, title)
        val searchTitleRequest = SearchTitleRequest(searchUrlProvider)
        val pageId = searchTitleRequest.makeRequest()

        val articleUrlProvider = ArticleFromPageIdUrlProvider(language, pageId)
        val articleContentRequest = ArticleContentRequest(articleUrlProvider)

        call.respond(articleContentRequest.makeRequest())
    }
}
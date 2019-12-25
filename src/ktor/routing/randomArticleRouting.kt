package ktor.routing

import wiki_media.request.RandomArticleRequest
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.Routing
import io.ktor.routing.get

fun Routing.randomArticle(){
    get("/random-article") {
        val randomArticleRequest = RandomArticleRequest(call.request.queryParameters)

        randomArticleRequest.makeRequest()

        //Set header to json
        call.respond(mapOf("OK" to true))
    }
}
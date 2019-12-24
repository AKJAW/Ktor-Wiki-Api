package com.akjaw

import com.akjaw.errors.WikiError
import com.akjaw.routes.random_article.randomArticle
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.features.ContentNegotiation
import io.ktor.features.StatusPages
import io.ktor.http.HttpStatusCode
import io.ktor.jackson.jackson
import io.ktor.routing.routing

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(StatusPages) {
        exception<WikiError> { cause ->
            call.respond(HttpStatusCode.BadRequest, cause.message ?: "")
        }
    }
    install(ContentNegotiation) {
        jackson { }
    }

    routing {
        randomArticle()
    }
}

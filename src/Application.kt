package com.akjaw

import com.akjaw.errors.WikiError
import com.akjaw.routes.randomArticle
import com.akjaw.util.WikiLanguage
import com.akjaw.util.isLanguageCorrect
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.client.*
import io.ktor.client.engine.apache.*
import io.ktor.content.TextContent
import io.ktor.features.ContentNegotiation
import io.ktor.features.StatusPages
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.withCharset
import io.ktor.jackson.jackson
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.util.getOrFail
import io.ktor.util.pipeline.PipelineContext
import java.lang.NullPointerException

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

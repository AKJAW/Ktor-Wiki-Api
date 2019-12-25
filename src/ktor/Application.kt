package ktor

import wiki_media.error.WikiError
import ktor.injection.languageModule
import ktor.injection.urlProviderModule
import ktor.routing.randomArticle
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.features.ContentNegotiation
import io.ktor.features.StatusPages
import io.ktor.http.HttpStatusCode
import io.ktor.jackson.jackson
import io.ktor.routing.routing
import org.koin.ktor.ext.Koin

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
    install(Koin){
        modules(listOf(
            languageModule,
            urlProviderModule
        ))
    }

    routing {
        randomArticle()
    }
}

package com.akjaw

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.client.*
import io.ktor.client.engine.apache.*
import io.ktor.features.ContentNegotiation
import io.ktor.jackson.jackson
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.util.getOrFail

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(ContentNegotiation) {
        jackson { }
    }

    routing {
        get("/random-article") {
            call.respond(mapOf("OK" to call.request.queryString()))
        }
    }

}


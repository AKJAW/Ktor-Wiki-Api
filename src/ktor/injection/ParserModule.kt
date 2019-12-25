package ktor.injection

import kotlinx.serialization.json.JsonObject
import org.koin.dsl.bind
import org.koin.dsl.module
import wiki_media.data.ArticleResponse
import wiki_media.request.parser.ArticleParser
import wiki_media.request.parser.WikiMediaParser

val parserModule = module {
    single { ArticleParser() as WikiMediaParser<JsonObject, ArticleResponse> }
}
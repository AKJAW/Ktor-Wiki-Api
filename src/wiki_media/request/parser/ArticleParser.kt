package wiki_media.request.parser

import kotlinx.serialization.json.JsonLiteral
import kotlinx.serialization.json.JsonObject
import util.getJsonLiteralContentOrThrow
import util.getJsonObjectOrThrow
import wiki_media.data.ApiArticleResponse
import wiki_media.error.WikiError

class ArticleParser: ResponseParser<JsonObject, ApiArticleResponse>{
    override fun parse(content: JsonObject): ApiArticleResponse {
        val articleJsonObject = getArticleJson(content)
        return createArticleResponse(articleJsonObject)
    }

    private fun getArticleJson(content: JsonObject): JsonObject{
        val query = content.getJsonObjectOrThrow("query")
        val pages = query.getJsonObjectOrThrow("pages")

        return pages.entries.firstOrNull()?.value as? JsonObject
            ?: throw WikiError.WikiResponseMissingKey.missingKey("articleId")
    }

    private fun createArticleResponse(articleJsonObject: JsonObject): ApiArticleResponse {
        val original = articleJsonObject["original"] as? JsonObject
        val image = original?.getJsonLiteralContentOrThrow("source")

        return ApiArticleResponse(
            pageId = articleJsonObject.getJsonLiteralContentOrThrow("pageid"),
            name = articleJsonObject.getJsonLiteralContentOrThrow("title"),
            description = articleJsonObject.getJsonLiteralContentOrThrow("extract"), //TODO check for an alternative
            image = image,
            url = articleJsonObject.getJsonLiteralContentOrThrow("fullurl")
        )
    }
}
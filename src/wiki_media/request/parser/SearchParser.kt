package wiki_media.request.parser

import kotlinx.serialization.json.JsonLiteral
import kotlinx.serialization.json.JsonObject
import util.getJsonArrayOrThrow
import util.getJsonLiteralContentOrThrow
import util.getJsonObjectOrThrow
import wiki_media.data.ApiArticleResponse
import wiki_media.error.WikiError

class SearchParser: ResponseParser<JsonObject, String>{
    override fun parse(content: JsonObject): String {
        return getArticleNumber(content)
    }

    private fun getArticleNumber(content: JsonObject): String{
        val query = content.getJsonObjectOrThrow("query")
        val search = query.getJsonArrayOrThrow("search")
        val article = search.getOrNull(0) as? JsonObject
            ?: throw WikiError.WikiResponseMissingKey.missingKey("searched article")

        return article.getJsonLiteralContentOrThrow("pageid")
    }

}
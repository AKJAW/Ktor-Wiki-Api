package wiki_media.request.parser

import kotlinx.serialization.json.JsonObject
import wiki_media.data.ArticleResponse

class ArticleParser: WikiMediaParser<JsonObject, ArticleResponse>{
    override fun parse(content: JsonObject): ArticleResponse {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
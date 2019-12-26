package wiki_media.request

import wiki_media.data.ArticleResponse

interface ApiRequest {
    suspend fun makeRequest(): ArticleResponse ///TODO
}
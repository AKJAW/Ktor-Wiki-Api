package wiki_media.request

import wiki_media.data.ArticleResponse

interface WikiMediaRequest {
    suspend fun makeRequest(): ArticleResponse ///TODO
}
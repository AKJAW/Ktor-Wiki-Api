package wiki_media.request

import wiki_media.data.WikiApiArticle

interface ApiRequest {
    suspend fun makeRequest(): WikiApiArticle
}
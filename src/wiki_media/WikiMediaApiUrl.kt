package com.akjaw.wiki_media

import com.akjaw.errors.WikiError

object WikiMediaApiUrl {
   fun createBaseUrl(language: String): String {
        return "https://$language.wikipedia.org/w/api.php"
    }

}
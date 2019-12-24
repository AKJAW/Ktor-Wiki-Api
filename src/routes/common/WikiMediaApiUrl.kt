package com.akjaw.routes.common

import com.akjaw.errors.WikiError
import com.akjaw.util.WikiLanguage
import com.akjaw.util.isLanguageCorrect

object WikiMediaApiUrl {
    fun create(language: WikiLanguage): String {
        if(language.isLanguageCorrect()){
            return createBaseUrl(language.toLowerCase())
        } else {
            throw WikiError.LanguageIncorrectError()
        }
    }

    private fun createBaseUrl(language: WikiLanguage): String {
        return "https://$language.wikipedia.org/w/api.php"
    }
}
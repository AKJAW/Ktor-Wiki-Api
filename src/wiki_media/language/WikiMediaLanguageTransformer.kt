package com.akjaw.wiki_media.language

import com.akjaw.errors.WikiError

class WikiMediaLanguageTransformer(private val validator: LanguageValidator): LanguageTransformer{
    override fun transform(language: String): String {
        if(validator.isValid(language)){
            return language.toLowerCase()
        } else {
            throw WikiError.LanguageIncorrectError()
        }
    }
}
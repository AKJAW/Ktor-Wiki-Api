package wiki_media.language

import wiki_media.error.WikiError

class WikiMediaLanguageTransformer(private val validator: LanguageValidator): LanguageTransformer {
    override fun transform(language: String): String {
        if(validator.isValid(language)){
            return language.toLowerCase()
        } else {
            throw WikiError.LanguageIncorrectError()
        }
    }
}
package wiki_media.language

interface LanguageTransformer {
    fun transform(language: String): String
}
package wiki_media.language

class LanguageValidator(validLanguages: List<String>){
    private val validLanguages = validLanguages.map { it.toLowerCase() }

    fun isValid(language: String): Boolean{
        return when(language.toLowerCase()){
            in validLanguages -> true
            else -> false
        }
    }
}
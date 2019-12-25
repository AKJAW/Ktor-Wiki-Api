package ktor.injection

import wiki_media.language.LanguageTransformer
import wiki_media.language.LanguageValidator
import wiki_media.language.WikiMediaLanguageTransformer
import org.koin.dsl.module

private val validLanguages = listOf("pl", "en")

val languageModule = module {
    single<LanguageTransformer> { WikiMediaLanguageTransformer(get()) }
    single { LanguageValidator(validLanguages) }
}
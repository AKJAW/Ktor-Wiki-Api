package com.akjaw.injection

import com.akjaw.wiki_media.language.LanguageTransformer
import com.akjaw.wiki_media.language.LanguageValidator
import com.akjaw.wiki_media.language.WikiMediaLanguageTransformer
import org.koin.dsl.module

private val validLanguages = listOf("pl", "en")

val languageModule = module {
    single<LanguageTransformer> { WikiMediaLanguageTransformer(get()) }
    single { LanguageValidator(validLanguages) }
}
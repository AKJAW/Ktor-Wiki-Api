package com.akjaw.wiki_media.language

interface LanguageTransformer {
    fun transform(language: String): String
}
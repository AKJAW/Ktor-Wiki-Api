package com.akjaw.errors

sealed class WikiError(message: String): Throwable(message) {
    class LanguageMissingError(message: String = "Language was not provided inside query"): WikiError(message)
    class LanguageIncorrectError(message: String = "Language is not correct"): WikiError(message)
}
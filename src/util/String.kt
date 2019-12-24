package com.akjaw.util

typealias WikiLanguage = String

fun WikiLanguage.isLanguageCorrect(): Boolean {
    return when(this.toLowerCase()){
        "pl", "en" -> true
        else -> false
    }
}
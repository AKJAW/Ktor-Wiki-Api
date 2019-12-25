package com.akjaw.wiki_media.url

interface WikiMediaUrlProvider {
    fun create(language: String): String
}
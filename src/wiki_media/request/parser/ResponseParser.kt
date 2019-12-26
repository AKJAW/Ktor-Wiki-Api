package wiki_media.request.parser

interface ResponseParser<I, R> {
    fun parse(content: I): R
}
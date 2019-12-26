package wiki_media.request.scraper

interface Scraper <T> {
    fun scrape(url: String): T
}
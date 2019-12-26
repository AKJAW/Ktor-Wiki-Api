package wiki_media.request.scraper

interface WikiMediaScraper <T> {
    fun scrape(url: String): T
}
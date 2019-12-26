package wiki_media.request.scraper

class ArticleLinkScraper: WikiMediaScraper<List<String>> {
    override fun scrape(url: String): List<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

fun main() {
    val scraper = ArticleLinkScraper()
    scraper.scrape("https://pl.wikipedia.org/wiki/FK_Haradzieja")
}
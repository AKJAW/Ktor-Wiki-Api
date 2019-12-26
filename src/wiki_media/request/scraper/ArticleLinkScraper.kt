package wiki_media.request.scraper

import org.jsoup.Jsoup
import org.jsoup.nodes.Element

class ArticleLinkScraper: Scraper<List<String>> {
    override fun scrape(url: String): List<String> {
        val doc = Jsoup.connect(url).get()

        val titles = doc.select("#mw-content-text a")
            .filter { filterOut(it) }
            .map { getTitle(it) }

        return titles
    }

    private fun getTitle(element: Element): String {
        return element.attr("href").substring(6)
    }

    private fun filterOut(element: Element): Boolean {
        val isWikiLink = element.hasAttr("href") &&
                element.attr("href").contains("/wiki/")
        val isATextNode = element.allElements.size == 1
        val doesLinkContainColon = element.attr("href").contains(":")

        return isWikiLink && isATextNode && !doesLinkContainColon
    }
}

fun main() {
    val scraper = ArticleLinkScraper()
    val a = scraper.scrape(	"https://pl.wikipedia.org/wiki/Adeste_Fideles")
    print(a)
}
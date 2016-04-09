package eu.matu.series

import eu.matu.site.Site
import org.junit.Test

class SeriesServiceTest extends GroovyTestCase {

  @Test
  void test_chapter_url_construction_works() {
    Site site = new Site(url: "https://www.wutworld.com")
    Series series = new Series(site: site,
        seriesCategory: "btth-index",
        seriesPrefix: "btth-chapter",
        chapterInfo: new ChapterInfo(latestChapter: 333)  )

    assert SeriesService.constructNextChapterUrl(series) == "https://www.wutworld.com/btth-index/btth-chapter-334/"
  }

  @Test
  void test_url_with_book_works() {
    Site site = new Site(url: "https://www.wutworld.com")
    Series issth = new Series(
        site: site,
        urlPattern: "issth-book-@latestBook-chapter-@latestChapter",
        seriesCategory: "issth-index",
        seriesPrefix: "issth-book",
        chapterInfo: new ChapterInfo(latestChapter: 147, latestBook: 2)
    )

    assert SeriesService.constructNextChapterUrl(issth) == "https://www.wutworld.com/issth-index/issth-book-2-chapter-148/"
  }

  @Test
  void test_parsing_empty_chapter_should_return_null() {
    SeriesService seriesService = new SeriesService()
    Site site = new Site(url: "https://www.wutworld.com")
    Series series = new Series(site: site,
        seriesCategory: "btth-index",
        seriesPrefix: "btth-chapter",
        chapterInfo: new ChapterInfo(latestChapter: 333)  )

    String requestContent = ""
    def parsedChapter = seriesService.parseChapter(series, requestContent)

    assert parsedChapter == null
  }

}

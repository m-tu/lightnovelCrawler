package eu.matu.series

import eu.matu.site.Site
import org.junit.Test

class SeriesServiceTest extends GroovyTestCase {

  @Test
  void testConstructNextChapterUrl() {
    SeriesService seriesService = new SeriesService()
    Site site = new Site(url: "https://www.wuxiaworld.com")
    Series series = new Series(site: site,
        urlPattern: "1",
        seriesCategory: "btth-index",
        seriesPrefix: "btth-chapter",
        latestChapter: 334)

    assert seriesService.constructNextChapterUrl(series) == "https://www.wuxiaworld.com/btth-index/btth-chapter-334/"
  }
}

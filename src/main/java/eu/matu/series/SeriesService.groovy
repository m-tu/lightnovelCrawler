package eu.matu.series

import eu.matu.site.Site
import eu.matu.util.RequestHelper
import groovy.util.logging.Slf4j
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.springframework.stereotype.Service

@Service
@Slf4j
class SeriesService {

  List<Series> getActiveSeries() {
    Site s = new Site(url: "http://localhost:21343")

    Series btth = new Series(
        site: s,
        chapterInfo: new ChapterInfo(latestChapter: 349),
        seriesCategory: "btth-index", seriesPrefix: "btth-chapter")

    Series issth = new Series(
        site: s,
        urlPattern: "issth-book-@latestBook-chapter-@latestChapter",
        chapterInfo: new ChapterInfo(latestChapter: 145, latestBook: 2),
        seriesCategory: "issth-index", seriesPrefix: "issth-chapter")

    [btth, issth]
  }

  String parseChapter(Series series, String response) {
    log.debug("Parsing series: {} chapter: {}", series.seriesCategory, series.chapterInfo.latestChapter+1, )
    try {
      Document doc = Jsoup.parse(response);
      Element body = doc.body();
      def parsedChapter = body.getElementById("primary") != null ? 'mockContent' : null
      log.debug("Parse result: {}", parsedChapter)
      return parsedChapter
    } catch (Exception e) {
      log.debug("Get exception while parsing chapter: {}", e)
      return null
    }
  }

  //TODO if some exception here, we need to check for it and handle unsuccessful parse
  void handleChapter(String chapter) {

  }

  //We need to keep checking for new chapters until there is no new ones
  //so we can only ask for one chapter at the time
  //after we get response we either check for new one if this one was real chapter or stop
  int sync(Series series) {
    String nextChapUrl = constructNextChapterUrl(series)
    def response = RequestHelper.makeRequest(nextChapUrl)
    String parsedChapter = parseChapter(series, response)

    while(parsedChapter != null) {
      series.chapterInfo.latestChapter = series.chapterInfo.latestChapter + 1
//      handleChapter(parsedChapter)
      response = RequestHelper.makeRequest(nextChapUrl)
      parsedChapter = parseChapter(series, response)
      nextChapUrl = constructNextChapterUrl(series)
      Thread.sleep(1000) //TODO in case we get stuck in the loop, remove later
    }

    series.chapterInfo.latestChapter
  }

  static String constructNextChapterUrl(Series series) {
    String mainPart = series.site.url + "/" + (series.seriesCategory ? series.seriesCategory + "/" : "");

    if(series.urlPattern != null) {
      String url = series.urlPattern
      series.chapterInfo.properties.each { prop, val ->
        if(url.contains("@${prop}")) {
          int nextChapt = prop.equals("latestChapter") ? val + 1 : val as int
          String propAsString = "@${prop}"
          url = url.replace( propAsString, nextChapt.toString())
        }
      }
      return mainPart + url + "/"
    }

    return mainPart + series.seriesPrefix + "-" + (series.chapterInfo.latestChapter + 1) + "/"
  }
}

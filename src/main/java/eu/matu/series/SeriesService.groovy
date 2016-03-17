package eu.matu.series

class SeriesService {

  List<Series> getActiveSeries() {
    []
  }

  static String constructNextChapterUrl(Series series) {
    return series.site.url + "/" +
        (series.seriesCategory ? series.seriesCategory + "/" : "") +
        series.seriesPrefix + "-" + series.latestChapter + "/"
  }
}

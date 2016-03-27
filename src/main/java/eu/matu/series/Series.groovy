package eu.matu.series

import eu.matu.site.Site

class Series {

  String displayName
  String urlPattern // {seriesPrefix}-{latestChapter}
  String seriesCategory
  String seriesPrefix   ///btth-index/btth-chapter-334/
  ChapterInfo chapterInfo

  Site site
  boolean active

  //page layout

}

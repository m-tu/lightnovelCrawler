package eu.matu.series

import eu.matu.site.Site

class Series {

  String displayName
  String urlPattern // {seriesPrefix}-{latestChapter}
  String seriesCategory
  String seriesPrefix   ///btth-index/btth-chapter-334/
  int latestChapter
  int latestBook
  int latestPart

  Site site
  boolean active

  //page layout

}

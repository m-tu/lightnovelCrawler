package eu.matu.stufflol

import eu.matu.site.Site
import eu.matu.site.SiteService
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
@Slf4j
class TheUpdater {

  @Autowired
  SiteService siteService

  void update() {

    log.info("Doing some real updating right now ")

    siteService.getAllActiveSites().each {
      switch (it.type) {
        case Site.SourceType.RSS:
          log.debug("Getting updates for site with url: {}", it.url)

          //get latest 10 entries from RSS feed

          //check enabled series list to see which ones to crawl for

          break
        case Site.SourceType.URL:
          log.debug("Getting updates for site with url: {}", it.url)
          break
        case Site.SourceType.CUSTOM:
          log.debug("Getting updates for site with url: {}", it.url)
          break
        default:
          log.error("Trying to get updates for site with unknown source type: {}", it)
      }

    }

  }

}

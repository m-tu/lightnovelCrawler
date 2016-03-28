package eu.matu.util

import eu.matu.series.SeriesService
import eu.matu.site.Site
import eu.matu.site.SiteService
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import java.util.concurrent.Callable
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future

@Service
@Slf4j
class TheUpdater {

  @Autowired
  SiteService siteService
  @Autowired
  SeriesService seriesService

  void initialSync() {

    def series = seriesService.getActiveSeries();
    series.each {
      log.debug("Got the following serie: {}", it.seriesCategory)
    }

    ExecutorService taskExecutor = Executors.newFixedThreadPool(series.size());
    List<Callable<?>> tasks = [];

    series.each {
        tasks.push(new Callable<Object>() {
          @Override
          Object call() throws Exception {
            return seriesService.sync(it)
          }
        })
    }
    log.debug("Waiting for all responses to return")
    List<Future<Integer>> futures = taskExecutor.invokeAll(tasks);

    futures.each {
      log.debug("Got responses: {}", it.get())
    }

  }

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

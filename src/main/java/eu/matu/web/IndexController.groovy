package eu.matu.web

import eu.matu.stufflol.TheUpdater
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@Slf4j
class IndexController {

  @Autowired TheUpdater updater;

  @RequestMapping("/")
  String home(@RequestParam String url) {

//    log.debug("Asking for feed: {} ", url)
//
//    URL feedUrl = new URL(url);
//
//    SyndFeedInput input = new SyndFeedInput();
//    SyndFeed feed = input.build(new XmlReader(feedUrl));
//
//    log.debug("Got the following feed: {}", feed)

    return "Hello World!"
  }


  @RequestMapping("/update")
  String updates() {

    updater.update()

    return "Updates"
  }

}

package eu.matu.web

import eu.matu.util.TheUpdater
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@Slf4j
class IndexController {

  @Autowired TheUpdater updater;

  @RequestMapping("/")
  String home() {
    return "Hello World!"
  }

  @RequestMapping("/update")
  String updates() {

    updater.initialSync()

    return "Updates"
  }

}


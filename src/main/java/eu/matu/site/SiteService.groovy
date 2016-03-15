package eu.matu.site

import org.springframework.stereotype.Service

@Service
class SiteService {

  public List<Site> getAllActiveSites() {

    def sites = [];

    sites.push(new Site(url: "http://www.wuxiaworld.com/feed/", type: Site.SourceType.RSS, active: true))

    sites
  }

}

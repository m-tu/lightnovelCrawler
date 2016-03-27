package eu.matu.util

import groovy.util.logging.Slf4j

@Slf4j
class RequestHelper {

  static String makeRequest(String url) {
    String response = ""
    try {
      log.debug("Making new request to url: {}", url)
      URL oracle = new URL(url)
      URLConnection connection = oracle.openConnection();
      String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.87 Safari/537.36"
      connection.setRequestProperty("User-Agent", userAgent)

      BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))

      String inputLine

      while ((inputLine = reader.readLine()) != null) {
        response += inputLine
      }
      reader.close();
      log.debug("Request finished: {}", response.length())
    } catch (IOException e) {
      log.debug("Request error: {}", e)
    }

    return response
  }

}

package eu.matu.site

class Site {

  String url
  boolean active
  SourceType type;

  enum SourceType {
    RSS,
    URL,
    CUSTOM
  }
}

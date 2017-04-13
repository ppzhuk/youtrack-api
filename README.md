# Java API for YouTrack REST API

YouTrack REST API Reference:

- http://confluence.jetbrains.com/display/YTD5/YouTrack+REST+API+Reference

##Sample:

    :::java
    YouTrackSession session = YouTrack.createSession("http://youtrackserver:8080/", "username", "password");
    Issue issue = session.getIssue("SAMPLE-1");
    issue.addComment("Test Comment from API");
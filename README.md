# Java API for YouTrack REST API

YouTrack REST API Reference:

- https://confluence.jetbrains.com/display/YTD65/YouTrack+REST+API+Reference

### Sample

Get all project's issues:
```java
    YouTrackSession session = YouTrack.createSession("http://youtrackserver:8080/", "username", "password");
    List<Issue> issues = session.getIssuesByProject("PROJECT_ID");
    System.out.println(issues.get(0).getId());
    System.out.println(issues.get(0).getDescription());
```

Add comment to issue:
```java
    YouTrackSession session = YouTrack.createSession("http://youtrackserver:8080/", "username", "password");
    Issue issue = session.getIssue("SAMPLE-1");
    issue.addComment("Test Comment from API");
```

Import issue:
```java
    YouTrackSession session = YouTrack.createSession("http://youtrackserver:8080/", "username", "password");
        IssueInput issueInput = new IssueInput("PROJECT_ID", "Summary", "Description");
        Issue issue = session.createIssue(issueInput);

        // updating issue is possible after creation
        issue.changeState(State.FIXED);

        // changing custom fields
        Map<String, String> fields = new HashMap<String, String>();
        fields.put("field1", "value1");
        fields.put("field2", "value2");
        issue.changeMultipleFields(fields);

        // applying custom command
        issue.executeCommand("star me Fixed field2 newValue");
```
YouTrack commands:

- https://www.jetbrains.com/help/youtrack/standalone/2017.1/Commands.html

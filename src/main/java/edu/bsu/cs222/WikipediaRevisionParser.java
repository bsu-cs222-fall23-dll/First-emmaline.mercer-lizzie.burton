package edu.bsu.cs222;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class WikipediaRevisionParser {


    private static final DateTimeFormatter TIMESTAMP_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

    public List<Revision> parseRevisions(String jsonData) throws IOException {
        JSONArray revisionsJson = JsonPath.read(jsonData, "$.query.pages.[*].revisions[*]");
        List<Revision> revisions = new ArrayList<>();
        for (Object revisionObj : revisionsJson) {
            String user = JsonPath.read(revisionObj, "$.user");
            String timestamp = JsonPath.read(revisionObj, "$.timestamp");

            revisions.add(new Revision(user, LocalDateTime.parse(timestamp, TIMESTAMP_FORMATTER)));
        }

        return revisions;
    }

    public boolean isRedirected(String jsonData) {
        Object redirectJson = JsonPath.read(jsonData, "$.query.redirects");
        return redirectJson != null;
    }

    public String getRedirectTitle(String jsonData) {
        if (isRedirected(jsonData)) {
            return JsonPath.read(jsonData, "$.query.redirects[0].to");
        }
        return null;
    }

    public boolean hasError(String jsonData) {
        Object errorJson = JsonPath.read(jsonData, "$.error");
        return errorJson != null;
    }

    public String getErrorMessage(String jsonData) {
        if (hasError(jsonData)) {
            return JsonPath.read(jsonData, "$.error.info");
        }
        return null;
    }
}

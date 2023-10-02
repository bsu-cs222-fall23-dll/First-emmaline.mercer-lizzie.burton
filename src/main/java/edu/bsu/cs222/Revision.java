package edu.bsu.cs222;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Revision {

    private String username;
    private LocalDateTime timestamp;

    public Revision(String username, LocalDateTime timestamp) {
        this.username = username;
        this.timestamp = timestamp;
    }

    public void printListOFAllRevisions(String articleTitle) throws IOException {
        WikipediaRevisionReader revisionReader = new WikipediaRevisionReader();
        ArrayList<String> revisedArray = revisionReader.readParsedData(articleTitle);
        System.out.println("\nArticle Changes: ");
        for (Object revision : revisedArray) {
            String revisionUserName = JsonPath.read(revision, "$.user");
            String revisionTimestamp = JsonPath.read(revision, "$.timestamp");
            System.out.println("Timestamp: " + revisionTimestamp + " " + "User: " + revisionUserName);
        }
    }

    public String getUsername() {
        return username;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Revision made by " + username + " at " + timestamp;
    }
}

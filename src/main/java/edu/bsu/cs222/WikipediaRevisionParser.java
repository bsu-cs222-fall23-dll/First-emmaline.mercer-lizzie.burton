package edu.bsu.cs222;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;

public class WikipediaRevisionParser {
    public ArrayList<String> revisionsParser(String testDataStream) throws IOException {
        try {
            ArrayList<String> revisions = JsonPath.read(testDataStream, "$.query.pages.[*].revisions[*]"); //.query.pages.[*].

            if (revisions != null) {
                int lengthLimit = Math.min(revisions.size(), 13);
                ArrayList<String> theFirst13Revisions = new ArrayList<>();
                for (int i = 0; i < lengthLimit; i++) {
                    theFirst13Revisions.add((String) revisions.get(i));
                }
                return theFirst13Revisions;
            } else {
                throw new IOException("No revisions found in JSON data");
            }
        } catch (Exception e) {
            throw new IOException("Error parsing JSON response: " + e.getMessage());
        }
    }

    public JSONArray timestampParser(String testDataStream) {
        return JsonPath.read(testDataStream, "$..timestamp");
    }

    public JSONArray usernameParser(String testDataStream) {
        return JsonPath.read(testDataStream, "$..user");
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
}
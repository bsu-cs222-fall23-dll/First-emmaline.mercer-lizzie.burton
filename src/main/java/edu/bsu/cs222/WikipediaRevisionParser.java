package edu.bsu.cs222;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import java.io.IOException;

public class WikipediaRevisionParser {
    public JSONArray revisionsParser(String testDataStream) throws IOException {
        try {
            JSONArray revisions = JsonPath.read(testDataStream, "$.query.pages.*.revisions[*]");

            if (revisions != null) {
                int lengthLimit = Math.min(revisions.size(), 13);
                JSONArray theFirst13Revisions = new JSONArray();
                for (int i = 0; i < lengthLimit; i++) {
                    theFirst13Revisions.add(revisions.get(i));
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
}
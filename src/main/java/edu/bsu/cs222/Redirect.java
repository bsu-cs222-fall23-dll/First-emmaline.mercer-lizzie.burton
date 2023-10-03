package edu.bsu.cs222;

import com.jayway.jsonpath.JsonPath;

public class Redirect {

    public static String getRedirectedTitle(String jsonData) {
        try {
            String redirectedTitle = JsonPath.read(jsonData, "$.query.redirects[0].to");
            return redirectedTitle;
        } catch (Exception e) {
            return null;
        }
    }
}

package edu.bsu.cs222;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

public class Redirect {
    public void isRedirected(Object parsedDataStream) {
        JSONArray redirection = JsonPath.read(parsedDataStream, "$..to");
        if(!redirection.isEmpty()) {
            System.out.println("\nRedirected to " + redirection.get(0).toString());
        }
    }
}

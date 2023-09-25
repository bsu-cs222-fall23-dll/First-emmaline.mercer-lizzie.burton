package edu.bsu.cs222;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class WikipediaRevisionParser {
    /*
        - turns the data in the testDataStream into an array called result
        - uses JSON because it's already in a JSONArray
     */

    public String timestampParser(String testDataStream) throws IOException {
        JSONArray result = (JSONArray) JsonPath.read(testDataStream, "$..timestamp");
        return result.get(0).toString();
    }

    public String usernameParser(String testDataStream) throws IOException {
        JSONArray result = (JSONArray) JsonPath.read(testDataStream, "$..username");
        return result.get(0).toString();
    }

}
package edu.bsu.cs222;

import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class WikipediaRevisionParserTests {

    private static String convertStreamToString(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream).useDelimiter("\\A");
        return scanner.hasNext() ? scanner.next() : "";
    }
    @Test
    public void testTimestampParse() throws IOException {
        WikipediaRevisionParser parser = new WikipediaRevisionParser();
        InputStream testDataStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("test.json");
        String testData = convertStreamToString(testDataStream);
        JSONArray timestamp = parser.timestampParser(testData);
        Assertions.assertEquals("2023-09-26T01:51:45Z", timestamp.get(0));
    }

    @Test
    public void testUsernameParse() throws IOException {
        WikipediaRevisionParser parser = new WikipediaRevisionParser();
        String testDataStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("test.json").toString();
//        String testData = testDataStream.toString();
        JSONArray username = parser.usernameParser(testDataStream);
        Assertions.assertEquals("StefenTower", username.get(0));
    }

    @Test
    public void testParser() throws IOException {
        WikipediaRevisionParser parser = new WikipediaRevisionParser();
        InputStream testDataStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("test.json");
//        String testData = convertStreamToString(testDataStream);
        JSONArray revisions = parser.revisionsParser(String.valueOf(testDataStream));
        System.out.println(revisions);
        String revisionsString = "{\"user\":\"2403:580D:6977:1:EC6C:A030:68E7:65A7\",\"anon\":\"\",\"timestamp\":\"2023-09-25T23:07:44Z\"}";
        Assertions.assertEquals(testDataStream, revisions);
    }

    @Test
    public void testWorkingURL() throws IOException {
        WikipediaRevisionReader parser = new WikipediaRevisionReader();
        String url = parser.getConstructedURL("zappa").toString();
        Assertions.assertEquals("https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=" +
                "zappa&rvprop=timestamp|user&rvlimit=13&redirects", url);
    }
}
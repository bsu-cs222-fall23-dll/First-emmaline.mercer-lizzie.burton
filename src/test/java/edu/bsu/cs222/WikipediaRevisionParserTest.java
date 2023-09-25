package edu.bsu.cs222;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
public class WikipediaRevisionParserTest {

    @Test
    public void testTimestampParse() throws IOException {
        WikipediaRevisionParser parser = new WikipediaRevisionParser();
        InputStream testDataStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("test.json");
        String timestamp = parser.timestampParser(testDataStream);
        Assertions.assertEquals("2023-09-21T08:36:42Z", timestamp);
    }

    @Test
    public void testUsernameParse() throws IOException {
        WikipediaRevisionParser parser = new WikipediaRevisionParser();
        InputStream testDataStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("test.json");
        String username = parser.usernameParser(testDataStream);
        Assertions.assertEquals("StefenTower", username);
    }
}

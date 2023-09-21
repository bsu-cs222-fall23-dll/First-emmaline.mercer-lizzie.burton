package edu.bsu.cs222;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
public class WikipediaAPIReaderTest {

    @Test
    public void testParse() {
        WikipediaRevisionParser parser = new WikipediaRevisionParser();
        InputStream testDataStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("test.json");
        String timestamp = parser.parser(testDataStream);
        Assertions.assertEquals("2023-09-21T08:36:42Z", timestamp);
    }
}

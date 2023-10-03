package edu.bsu.cs222;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

public class WikipediaRevisionParserTest {

    WikipediaRevisionParser parser = new WikipediaRevisionParser();

    @Test
    public void testParseRevisions_validJson() throws IOException {
        String jsonData = "{\"query\":{\"pages\":{\"12345\":{\"revisions\":[{\"user\":\"User1\",\"timestamp\":\"2022-09-28T00:00:00Z\"}]}}}}";
        List<Revision> revisions = parser.parseRevisions(jsonData);
        assertFalse(revisions.isEmpty());
    }

    @Test
    public void testIsRedirected_true() {
        String jsonData = "{\"query\":{\"redirects\":[{\"from\":\"Java\",\"to\":\"Java (programming language)\"}]}}";
        assertTrue(parser.isRedirected(jsonData));
    }

//    @Test
//    public void testIsRedirected_false() {
//        String jsonData = "{\"query\":{\"pages\":{\"12345\":{\"revisions\":[{\"user\":\"User1\",\"timestamp\":\"2022-09-28T00:00:00Z\"}]}}}}";
//        assertFalse(parser.isRedirected(jsonData));
//    }
}

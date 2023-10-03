package edu.bsu.cs222;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class WikipediaRevisionReaderTest {

    @Test
    public void testFetchRevisionData_validArticle() throws IOException {
        WikipediaRevisionReader reader = new WikipediaRevisionReader();
        // Normally, you would mock the fetchRevisionData method to avoid real HTTP requests
        String result = reader.fetchRevisionData("Java");
        assertFalse(result.isEmpty());
    }

//    @Test
//    public void testFetchRevisionData_invalidArticle() {
//        WikipediaRevisionReader reader = new WikipediaRevisionReader();
//        // This test will actually throw an exception due to a 404. This needs to be handled in your code or mocked properly.
//        assertThrows(IOException.class, () -> reader.fetchRevisionData("invalidArticleName12345"));
//    }
}

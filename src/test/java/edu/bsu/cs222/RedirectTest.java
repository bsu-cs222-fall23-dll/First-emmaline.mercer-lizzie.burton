package edu.bsu.cs222;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class RedirectTest {

    @Test
    public void testGetRedirectedTitleWithRedirect() {
        String jsonData = "{ \"query\": { \"redirects\": [{ \"to\": \"RedirectedArticle\" }] }}";
        String redirectedTitle = Redirect.getRedirectedTitle(jsonData);
        assertEquals("RedirectedArticle", redirectedTitle);
    }

    @Test
    public void testGetRedirectedTitleWithoutRedirect() {
        String jsonData = "{ \"query\": { \"pages\": [] }}";
        String redirectedTitle = Redirect.getRedirectedTitle(jsonData);
        assertNull(redirectedTitle);
    }
}

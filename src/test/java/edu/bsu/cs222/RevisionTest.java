package edu.bsu.cs222;

import org.junit.jupiter.api.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RevisionTest {

    private static final String SAMPLE_USERNAME = "JohnDoe";
    private static final String SAMPLE_TIMESTAMP = "2023-09-25T12:30:45+01:00";
    private Revision revision;

    @BeforeEach
    public void setUp() {
        revision = new Revision(SAMPLE_USERNAME, SAMPLE_TIMESTAMP);
    }

    @Test
    public void testGetUsername() {
        Assertions.assertEquals(SAMPLE_USERNAME, revision.getUsername());
    }

    @Test
    public void testGetTimestamp() {
        LocalDateTime expectedDate = LocalDateTime.parse(SAMPLE_TIMESTAMP, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        Assertions.assertEquals(expectedDate, revision.getTimestamp());
    }

    @Test
    public void testToString() {
        String expectedString = "Revision made by " + SAMPLE_USERNAME + " at " + revision.getTimestamp();
        Assertions.assertEquals(expectedString, revision.toString());
    }

    @AfterEach
    public void tearDown() {
        revision = null;
    }
}

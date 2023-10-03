package edu.bsu.cs222;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class RevisionTest {

    @Test
    public void testRevisionProperties() {
        LocalDateTime expectedTimestamp = LocalDateTime.of(2023, 9, 28, 12, 0);
        Revision revision = new Revision("TestUser", expectedTimestamp);

        assertEquals("TestUser", revision.getUsername());
        assertEquals(expectedTimestamp, revision.getTimestamp());
    }

    @Test
    public void testToString() {
        LocalDateTime timestamp = LocalDateTime.of(2023, 9, 28, 12, 0);
        Revision revision = new Revision("TestUser", timestamp);
        String expectedString = "Revision made by TestUser at " + timestamp;

        assertEquals(expectedString, revision.toString());
    }
}

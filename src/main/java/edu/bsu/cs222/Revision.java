package edu.bsu.cs222;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Revision {

    private String username;
    private LocalDateTime timestamp;

    public Revision(String username, String timestamp) {
        this.username = username;
        this.timestamp = LocalDateTime.parse(timestamp, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }

    public String getUsername() {
        return username;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Revision made by " + username + " at " + timestamp;
    }
}

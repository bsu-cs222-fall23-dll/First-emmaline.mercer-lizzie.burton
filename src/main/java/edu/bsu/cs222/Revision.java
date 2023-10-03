package edu.bsu.cs222;

import java.time.LocalDateTime;

public class Revision {
    private String username;

    private LocalDateTime timestamp;

    public Revision(String username, LocalDateTime timestamp) {
        this.username = username;
        this.timestamp = timestamp;
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

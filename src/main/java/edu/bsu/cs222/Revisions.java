package edu.bsu.cs222;

import java.time.LocalDateTime;

public class Revisions {
    private String userName;
    private LocalDateTime timeStamp;

    public Revisions(String userName, LocalDateTime timeStamp) {
        this.userName = userName;
        this.timeStamp = timeStamp;
    }

    public String getUserName() {
        return userName;
    }


    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    @Override
    public String toString() {
        return timeStamp + " " + userName;
    }
}

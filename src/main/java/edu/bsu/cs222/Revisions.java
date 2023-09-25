package edu.bsu.cs222;

public class Revisions {
    private String userName;
    private String timeStamp;

    public Revisions(String userName, String timeStamp) {
        this.userName = userName;
        this.timeStamp = timeStamp;
    }

    public String getUserName() {
        return userName;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    @Override
    public String toString() {
        return timeStamp + " " + userName;
    }
}
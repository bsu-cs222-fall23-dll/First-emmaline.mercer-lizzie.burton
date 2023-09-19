package edu.bsu.cs222;

public class User {
    private String userName;
    private String userID;

    public User(String userName, String userID) {
        this.userName = userName;
        this.userID = userID;
    }
//added
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "Username: " + userName + ", User ID: " + userID;
    }
}

package edu.bsu.cs222;

public class User {
    /* - this is the main
   - we are the users looking for
     the updates (changes)
 */

    /* TO-DO:
        - use JSON to parse from wikipedia
        - connect that with wikipediaarticle & wikipediaAPI
        - User will pull data from wikiart & wikiAPI
          to give the "user" the info they want
        - ErrorHandling will keep the errors from breaking
          my laptop
        - Updates will pull data from wikiart & wikiAPI
          and hold onto that data?? (up for interpretation)
        - print list of changes; pull data from Updates
     */

    private String userName;
    private String userID;

    public User(String userName, String userID) {
        this.userName = userName;
        this.userID = userID;
    }

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

package model;

public class UserProfile {

    // Private fields
    private String username;
    private String school;
    private String sessionName;

    // Constructor
    public UserProfile(String username, String school, String sessionName) {
        this.username = username;
        this.school = school;
        this.sessionName = sessionName;
    }

    // Getters
    public String getUsername() { return username; }
    public String getSchool() { return school; }
    public String getSessionName() { return sessionName; }
}

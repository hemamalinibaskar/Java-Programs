package libraryManagement;

public class User {
    private String username; // Username of the user
    private String password; // Password of the user

    // Constructor to initialize a User 
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Method to get the username of the user
    public String getUsername() {
        return username;
    }

    // Method to get the password of the user
    public String getPassword() {
        return password;
    }
}
